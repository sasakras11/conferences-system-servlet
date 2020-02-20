package com.command;

import com.context.AppContext;
import com.entity.User;
import com.service.SpeechService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowSpeechesCommand extends FrontCommand {

    private final SpeechService speechService;

    public ShowSpeechesCommand() {
        speechService = AppContext.getSpeechService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String conferenceId = req.getParameter("conferenceId");
        req.setAttribute("speeches",speechService.findAllSpeechesByConferenceId(conferenceId));
        User user = (User) req.getSession().getAttribute("user");
        forward(user.getStatus().name().toLowerCase()+"/speeches");

    }
}
