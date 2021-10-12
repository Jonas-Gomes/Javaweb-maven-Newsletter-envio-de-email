package ifpb.edu.br;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/sendemail")

public class SendEmailTLS extends HttpServlet{

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        final String username = "pw1.jonas.newsletter@gmail.com";
        final String password = "newsletter";
        String nome = (String) request.getParameter("nome");
        String emailuser = (String) request.getParameter("email");

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("pw1.jonas.newsletter@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(emailuser)
            );
            message.setSubject("Newsletter - PW1 - Jonas");
            message.setText("Email de teste para a disciplina de programação para web 1,"
                    + "\n\n Não responda este email");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        request.setAttribute("nome", nome);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);
    }

}