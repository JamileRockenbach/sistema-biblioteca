public class LivroDigital extends Livro {
    private String formatoArquivo;
    private double tamanhoArquivo;

    public LivroDigital() {

    }

    public LivroDigital(String titulo, String autor, int anoPublicacao, int numeroPaginas, String formatoArquivo,
            double tamanhoArquivo) {
        super(titulo, autor, anoPublicacao, numeroPaginas);
        this.formatoArquivo = formatoArquivo;
        this.tamanhoArquivo = tamanhoArquivo;
    }

    public String getFormatoArquivo() {
        return formatoArquivo;
    }

    public void setFormatoArquivo(String formatoArquivo) {
        if (formatoArquivo == null || formatoArquivo.trim().isEmpty()) {
            throw new IllegalArgumentException("O formato do arquivo não pode ser nulo ou vazio.");
        }
        this.formatoArquivo = formatoArquivo.trim();
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }

    public void setTamanhoArquivo(double tamanhoArquivo) {
        if (tamanhoArquivo <= 0) {
            throw new IllegalArgumentException("O tamanho do arquivo deve ser maior que zero.");
        }
        this.tamanhoArquivo = tamanhoArquivo;
    }

    @Override
    public String toString() {
        String dadosLivro = super.toString();
        return dadosLivro
                + ", Formato: " + this.getFormatoArquivo()
                + ", Tamanho: " + tamanhoArquivo;
    }
}
