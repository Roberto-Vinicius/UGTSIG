package br.com.formulario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.formulario.model.Curriculo;
import br.com.formulario.repository.CurriculoRepository;

@Service  // Define esta classe como um serviço gerenciado pelo Spring
public class CurriculoService {

    // =============== INJEÇÕES DE DEPENDÊNCIA ===============
    @Autowired  // Injeta o repositório para operações com o banco de dados
    private CurriculoRepository curriculumRepository;

    @Autowired  // Injeta o serviço de envio de e-mails
    private TesteEmail testeEmail;

    // =============== MÉTODOS DO SERVIÇO ===============
    /**
     * Salva um currículo no banco de dados e envia e-mail de notificação
     *
     * @param curriculum Objeto Curriculo a ser salvo
     */
    public void save(Curriculo curriculum) {
        // 1. Salva o currículo no banco de dados
        curriculumRepository.save(curriculum);

        // 2. Logs de depuração (seria melhor usar um sistema de logging)
        System.out.println(curriculum.getId());
        System.out.println("enviando..");

        // 3. Dispara o envio do e-mail com o currículo
        testeEmail.enviar(curriculum);

        // 4. Log adicional com o nome do candidato
        System.out.println("nome " + curriculum.getNome());
    }

    /**
     * Busca todos os currículos cadastrados
     *
     * @return Lista de todos os currículos
     */
    public List<Curriculo> getAllCurriculos() {
        return curriculumRepository.findAll();  // Usa o método padrão do JpaRepository
    }

    /**
     * Busca um currículo específico pelo ID
     *
     * @param id ID do currículo a ser buscado
     * @return Optional contendo o currículo, se encontrado
     */
    public Optional<Curriculo> getCurriculumById(Long id) {
        return curriculumRepository.findById(id);  // Retorna Optional para tratamento seguro de valores nulos
    }
}
