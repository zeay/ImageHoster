/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import java.time.LocalDate;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value = "/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("id") Integer imgId, @PathVariable("title") String title, @RequestParam("comment") String textStr, HttpSession session) {
        User user = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment();
        comment.setImage(imageService.getImage(imgId));
        comment.setText(textStr);
        comment.setUser(user);
        comment.setCreatedDate(LocalDate.now());
        commentService.insertComment(comment);
        return "redirect:/images/" + imgId + "/" + title;
    }

}
