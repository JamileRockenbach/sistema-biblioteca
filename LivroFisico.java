public class LivroFisico extends Livro {
    private int numeroExemplares;
    private String dimensoes;

    public LivroFisico() {

    }

    public LivroFisico(String titulo, String autor, int anoPublicacao, int numeroPaginas,
            int numeroExemplares, String dimensoes) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.numeroExemplares = numeroExemplares;
        this.dimensoes = dimensoes;
    }

    public int getNumeroExemplares() {
        return numeroExemplares;
    }

    public void setNumeroExemplares(int numeroExemplares) {
        if (numeroExemplares <= 0) {
            throw new IllegalArgumentException("Número de exemplares deve ser maior que zero.");
        }
        this.numeroExemplares = numeroExemplares;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setDimensoes(String dimensoes) {
        if (dimensoes == null || dimensoes.trim().isEmpty()) {
            throw new IllegalArgumentException("As dimensões não podem ser nulas ou vazias.");
        }
        this.dimensoes = dimensoes.trim();
    }

    @Override
    public String toString() {
        String dadosLivro = super.toString();
        return dadosLivro
                + ", Número de Exemplares: " + this.getNumeroExemplares()
                + ", Dimensão: " + dimensoes;
    }
}