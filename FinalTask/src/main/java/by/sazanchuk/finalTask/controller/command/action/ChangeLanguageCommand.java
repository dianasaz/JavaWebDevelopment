package by.sazanchuk.finalTask.controller.command.action;

import by.sazanchuk.finalTask.controller.command.Page;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ChangeLanguageCommand implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeLanguageCommand.class);

    private static final String LANGUAGE = "lang";
    private static final String EN = "EN";
    private static final String RU = "RU";
    private static final String NEXT_LANGUAGE = "nextLang";


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String language = request.getParameter(LANGUAGE).toUpperCase();
        if (language.equalsIgnoreCase(RU)) {
            language = EN;
        } else {
            language = RU;
        }
        String next = getNextLang(language);
        setAttributes(request, language, next);
        return new CommandResult("/controller?command=home_page");
    }

    public void setAttributes(HttpServletRequest request, String lang, String next) {
        HttpSession session = request.getSession();
        session.setAttribute(LANGUAGE, lang);
        session.setAttribute(NEXT_LANGUAGE, next);
    }

    private String getNextLang(String lang) {
        String next;
        if (lang.equalsIgnoreCase(RU)) {
            next = EN;
        } else {
            next = RU;
        }
        return next;
    }

}
