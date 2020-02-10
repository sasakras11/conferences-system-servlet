package com.command;

import com.context.AppContext;
import com.dao.CrudPageableConferenceDao;
import com.entity.Conference;
import com.entity.User;
import com.service.ConferenceService;
import com.service.util.Jsp.JspMap;
import com.service.util.Jsp.Stage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShowEditConferencePageCommand extends FrontCommand {

    private ConferenceService conferenceService;
    public ShowEditConferencePageCommand() {
        this.conferenceService = AppContext.getConferenceService();
    }

    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Conference conference = conferenceService.findConferenceById(id).get();
        HttpSession httpSession = req.getSession();
        User user = (User) req.getSession().getAttribute("user");
        httpSession.setAttribute("conferenceToEdit", conference);

        forward(JspMap.getJspUrl(user.getStatus(), Stage.EDIT_CONFERENCE));
    }
}
