import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private static final int ANO_PUBLICACAO_MINIMO = 1900;
    private List<Livro> acervo;

    public Biblioteca() {
        this.acervo = new ArrayList<>();
    }

    public Livro adicionar(Livro livro) throws Exception {
        if (livro == null)
            throw new Exception("Livro não pode ser nulo.");

        if (livro.getTitulo() == null || livro.getTitulo().trim().isEmpty())
            throw new Exception("Título não pode ser em branco.");
        livro.setTitulo(livro.getTitulo().trim());

        if (livro.getAutor() == null || livro.getAutor().trim().isEmpty())
            throw new Exception("Autor não pode ser em branco.");
        livro.setAutor(livro.getAutor().trim());

        int anoAtual = LocalDate.now().getYear();
        if (livro.getAnoPublicacao() < ANO_PUBLICACAO_MINIMO || livro.getAnoPublicacao() > anoAtual)
            throw new Exception("Ano de publicação deve estar entre " + ANO_PUBLICACAO_MINIMO + " e " + anoAtual);

        if (livro.getNumeroPaginas() <= 0)
            throw new Exception("Número de páginas deve ser maior que zero.");

        for (Livro l : acervo) {
            if (l.getTitulo().equalsIgnoreCase(livro.getTitulo()) &&
                    l.getAutor().equalsIgnoreCase(livro.getAutor()) &&
                    l.getAnoPublicacao() == livro.getAnoPublicacao()) {
                throw new Exception("Esse livro já está cadastrado.");
            }
        }

        acervo.add(livro);
        return livro;
    }

    public List<Livro> pesquisar() {
        return acervo;
    }

    public List<Livro> pesquisar(String titulo) {
        return pesquisar(titulo, null);
    }

    public List<Livro> pesquisar(String titulo, String autor) {
        List<Livro> livrosEncontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                if (autor == null || livro.getAutor().toLowerCase().contains(autor.toLowerCase())) {
                    livrosEncontrados.add(livro);
                }
            }
        }
        return livrosEncontrados;
    }

    public Livro remover(int indice) throws Exception {
        if (indice < 0 || indice >= acervo.size())
            throw new Exception("Índice inválido.");
        return acervo.remove(indice);
    }

    public void atualizar(int indice, Livro novoLivro) throws Exception {
        if (indice < 0 || indice >= acervo.size())
            throw new Exception("Índice inválido.");
        for (int i = 0; i < acervo.size(); i++) {
            if (i != indice &&
                    acervo.get(i).getTitulo().equalsIgnoreCase(novoLivro.getTitulo()) &&
                    acervo.get(i).getAutor().equalsIgnoreCase(novoLivro.getAutor()) &&
                    acervo.get(i).getAnoPublicacao() == novoLivro.getAnoPublicacao()) {
                throw new Exception("Já existe outro livro com esse título, autor e ano.");
            }
        }

        acervo.set(indice, novoLivro);
    }

    public int contarLivros() {
        return acervo.size();
    }

    public List<Livro> pesquisarAno(int anoInicio, int anoFim) {
        List<Livro> encontrados = new ArrayList<>();
        for (Livro livro : acervo) {
            if (livro.getAnoPublicacao() >= anoInicio && livro.getAnoPublicacao() <= anoFim) {
                encontrados.add(livro);
            }
        }
        return encontrados;
    }

    public Livro maisAntigo() {
        Livro livro = null;
        for (Livro l : acervo) {
            if (livro == null || l.getAnoPublicacao() < livro.getAnoPublicacao()) {
                livro = l;
            }
        }
        return livro;
    }

    public Livro maisNovo() {
        Livro livro = null;
        for (Livro l : acervo) {
            if (livro == null || l.getAnoPublicacao() > livro.getAnoPublicacao()) {
                livro = l;
            }
        }
        return livro;
    }
}