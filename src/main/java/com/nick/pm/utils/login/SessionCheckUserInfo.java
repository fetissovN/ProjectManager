package com.nick.pm.utils.login;

import com.nick.pm.DTO.UserDTO;
import com.nick.pm.converter.SpringConverterUserDTOToUser;
import com.nick.pm.entity.Project;
import com.nick.pm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.List;

@Component
public class SessionCheckUserInfo {

    @Autowired
    private SpringConverterUserDTOToUser converterUserDTOToUser;

    private boolean checkUserRelatedToPost(Long id, User user){
        List<Project> list = user.getUserProjects();
        for (Project p: list){
            if (p.getId()==id){
                return true;
            }
        }
        return false;
    }

    private User getUserFromSession(HttpSession session){
        UserDTO userDTO = (UserDTO) session.getAttribute("auth");
        return converterUserDTOToUser.convert(userDTO);
    }

//    public boolean checkUserRelatedToPost(Long id, HttpSession session){
//        User user = getUserFromSession(session);
//        return checkUserRelatedToPost(id,user);
//    }
//
//    public boolean checkUserRelatedToMessage(Long messageId, Long postId, HttpSession session){
//        User user = getUserFromSession(session);
//        if (checkUserRelatedToPost(postId,user)){
//            Project post = user.getSentPosts().get(Math.toIntExact(postId)-1);
//            List<Task> messages = post.getPostRelatedMessages();
//            for (Task m: messages){
//                if (m.getId()==messageId){
//                    return true;
//                }
//            }
//            return false;
//        }else {
//            return false;
//        }
//    }
}
