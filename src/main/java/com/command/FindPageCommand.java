package com.command;

import com.context.AppContext;
import com.dao.ConferenceGroup;
import com.entity.Conference;
import com.entity.User;
import com.service.ConferenceService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class FindPageCommand extends FrontCommand {

    private final ConferenceService conferenceService;

    public FindPageCommand() {
        this.conferenceService = AppContext.getConferenceService();
    }


    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Optional<String> pageComing = Optional.ofNullable(req.getParameter("pageComing"));
        Optional<String> pageFinished = Optional.ofNullable(req.getParameter("pageFinished"));
         List<Conference> conferences;
         if(pageComing.isPresent()){
             req.setAttribute("pageNum",pageComing.get());
             conferences = conferenceService.findAllConferences(Integer.parseInt(pageComing.get()),ConferenceGroup.COMING);
         }
         else{
             req.setAttribute("pageNum",pageFinished.get());
             conferences = conferenceService.findAllConferences(Integer.parseInt(pageFinished.get()),ConferenceGroup.FINISHED);
         }

         User user = (User)req.getSession().getAttribute("user");
         req.setAttribute("conferences",conferences);
         forward(user.getStatus().name().toLowerCase()+"/conferencesComing");

    }
}
