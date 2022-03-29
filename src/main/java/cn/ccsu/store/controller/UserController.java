package cn.ccsu.store.controller;


import cn.ccsu.store.controller.ex.*;
import cn.ccsu.store.entity.User;
import cn.ccsu.store.service.IUserService;
import cn.ccsu.store.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author 潇洒哥queen
 * @Date 2022/3/27 12:58
 * @Version 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    public static final int AVATAR_MAX_SIZE=10*1024*1024;
    public static final List<String> AVATAR_TYPE=new ArrayList<>();

    static {
        AVATAR_TYPE.add("image/jpeg");
        AVATAR_TYPE.add("image/png");
        AVATAR_TYPE.add("image/bmp");
        AVATAR_TYPE.add("image/gif");
    }

    @RequestMapping("/reg")
    public JsonResult<Void> reg(User user) {
        userService.reg(user);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/login")
    public JsonResult<User> login(String username, String password, HttpSession session) {
        User data = userService.login(username, password);
        //在session对象中完成数据绑定（session是全局的）
        session.setAttribute("uid", data.getUid());
        session.setAttribute("username", data.getUsername());
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/change_password")
    public JsonResult<Void> changePassword(String oldPassword, String newPassword, HttpSession session) {
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        userService.changePassword(uid, username, oldPassword, newPassword);
        return new JsonResult<>(OK);
    }

    @RequestMapping("/get_by_uid")
    public JsonResult<User> getByUid(HttpSession session) {
        User data = userService.getByUid(getUidFromSession(session));
        return new JsonResult<>(OK, data);
    }

    @RequestMapping("/change_info")
    public JsonResult<Void> changeInfo(User user, HttpSession session) {
        userService.changeInfo(getUidFromSession(session), getUsernameFromSession(session), user);
        return new JsonResult<>(OK);
    }

    /**
     * MultipartFile 接口时springmvc提供的一个接口，为我们包装了获取文件类型（任何文件类型都可以）springboot整合了springmvc只需要在参数列表上声明一个
     * MultipartFile类型的参数
     *
     * @param session 会话对象
     * @param file 头像文件
     */
    @RequestMapping("/change_avatar")
    public JsonResult<String> changeAvatar(HttpSession session, MultipartFile file) {
        if (file.isEmpty()) {
            throw new FileEmptyException("文件为空");
        }
        if (file.getSize()>AVATAR_MAX_SIZE){
            throw new FileSizeException("文件超出限制");
        }
        String contentType=file.getContentType();
        if (!AVATAR_TYPE.contains(contentType)){
            throw new FileTypeException("文件类型不支持");
        }
        String filename = file.getOriginalFilename(); //获取上传文件原来的名称
        String filePath = "src/main/resources/static/upload/";
        File temp = new File(filePath);
        if (!temp.exists()) {
            temp.mkdirs();
        }
        int index=filename.lastIndexOf(".");
        String suffix=filename.substring(index);
        String newFilename= UUID.randomUUID().toString().toUpperCase()+suffix;
        File localFile = new File(temp.getAbsolutePath()+File.separator+newFilename);
        try {
            file.transferTo(localFile); //把上传的文件保存至本地
        } catch (FileStateException e) {
            throw new FileStateException("文件状态异常");
        }catch (IOException e) {
            throw new FileUploadIOException("文件读写异常");
        }
        String avatar="/upload/"+newFilename;
        userService.changeAvatar(getUidFromSession(session),avatar,getUsernameFromSession(session));
        return new JsonResult<>(OK,avatar);
    }
}
