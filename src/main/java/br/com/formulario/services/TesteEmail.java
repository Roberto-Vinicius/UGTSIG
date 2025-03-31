package br.com.formulario.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import br.com.formulario.enumtipo.Status;
import br.com.formulario.model.Curriculo;
import br.com.formulario.model.EmailModel;
import br.com.formulario.repository.EmailRespository;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class TesteEmail {

    // Repositório para persistência dos registros de e-mail
    @Autowired
    private EmailRespository emailrespository;

    // Componente do Spring para envio de e-mails
    @Autowired
    private JavaMailSender javaMailSender;

    // Obtém o e-mail remetente das configurações do application.properties
    @Value("${spring.mail.username}")
    private String enviador;

    // Mapa de tipos MIME para extensões de arquivo comuns
    private static final Map<String, String> MIME_TYPES = new HashMap<>() {
        {
            put("pdf", "application/pdf");
            put("doc", "application/msword");
            put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
    };

    /**
     * Método principal para enviar e-mail com currículo anexado
     *
     * @param curriculo Objeto contendo os dados do currículo e candidato
     * @return String com status do envio ("Email enviado!!" ou "erro ao
     * enviar")
     */
    public String enviar(Curriculo curriculo) {
        // Prepara os dados do e-mail
        String destino = curriculo.getEmail();
        String titulo = "Curriculo Recebido";

        // Constroi o corpo do e-mail com informações personalizadas
        String texto = "Olá " + curriculo.getNome().toUpperCase() + ",\n\n"
                + "Seu currículo foi recebido e ficamos felizes em saber do seu interesse na vaga de "
                + curriculo.getCargoDesejado().toUpperCase() + ",\n\n"
                + "É ótimo ver que você está no nível de escolaridade: " + curriculo.getEscolaridade() + ",\n\n"
                + "Em breve, você receberá um Email: " + curriculo.getTelefone() + ".\n\n"
                + "Obrigado por se candidatar!\n\n"
                + "Atenciosamente,\n  SISTEMA DE EMAILS";

        // Cria e configura o modelo de e-mail para persistência
        EmailModel emailModel = new EmailModel();
        emailModel.setCurriculo_id(curriculo.getId());
        emailModel.setEnviador(enviador);
        emailModel.setReceptor(curriculo.getEmail());
        emailModel.setSubjt_titutlo("Envio de curriculo");
        emailModel.setTexto("Curriculo No banco!");
        emailModel.setData(LocalDateTime.now());
        emailModel.setCandidato(curriculo.getNome());

        try {
            // Prepara a mensagem de e-mail
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            // Configura remetente, destinatário, assunto e corpo do e-mail
            helper.setFrom(enviador);
            helper.setTo(destino);
            helper.setSubject(titulo);
            helper.setText(texto);

            // Processa o anexo do currículo
            String fileName = curriculo.getFileName();
            // Extrai a extensão do arquivo
            String fileExtension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
            // Obtém o tipo MIME apropriado ou usa um genérico se não encontrar
            String mimeType = MIME_TYPES.getOrDefault(fileExtension, "application/octet-stream");

            // Adiciona o anexo ao e-mail
            helper.addAttachment(fileName, new ByteArrayDataSource(curriculo.getArquivo(), mimeType));

            // Envia o e-mail
            javaMailSender.send(mimeMessage);

            // Marca o status como enviado
            emailModel.setStatus(Status.ENVIADO);
            return "Email enviado!!";

        } catch (Exception e) {
            // Em caso de erro, marca o status como falha
            emailModel.setStatus(Status.FALHA);
            return "erro ao enviar";
        } finally {
            // Salva o registro do e-mail no banco de dados, independentemente de sucesso ou falha
            emailrespository.save(emailModel);
        }
    }
}
