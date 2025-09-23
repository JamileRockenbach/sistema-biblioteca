import java.util.List;
import java.util.Scanner;

public class Main {
    private static Biblioteca biblioteca = new Biblioteca();
    private static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) { 
        String menu = """
                +----------------------------+
                |   Sistema de Biblioteca!   |
                +----------------------------+

                |Escolha uma das opções abaixo:
                |1 - Adicionar Livro
                |2 - Listar Acervo
                |3 - Pesquisar Livro
                |4 - Remover Livro
                |5 - Atualizar Livro
                |6 - Livros no total
                |7 - Pesquisa por ano
                |8 - Livro mais antigo/mais novo
                |0 - Sair

                """;

        int opcao;
        do {
            System.out.println(menu);
            opcao = Input.scanInt(">> Digite sua escolha: ", scan);
            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    pausar();
                    break;
                case 2:
                    listarAcervo();
                    pausar();
                    break;
                case 3:
                    pesquisarLivro();
                    pausar();
                    break;
                case 4:
                    removerLivro();
                    pausar();
                    break;
                case 5:
                    atualizarLivro();
                    pausar();
                    break;
                case 6:
                    contarLivros();
                    pausar();
                    break;
                case 7:
                    pesquisarAno();
                    pausar();
                    break;
                case 8:
                    antigoENovo();
                    pausar();
                    break;
                case 0:
                    System.out.println("Volte Sempre!! :)");
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (opcao != 0);
    }

    private static void cadastrarLivro() {
        String titulo = Input.scanString(">> Digite o Título: ", scan);
        String autor = Input.scanString(">> Digite o Autor: ", scan);
        int anoAtual = java.time.LocalDate.now().getYear();
        int anoPublicacao = Input.scanInt(">> Digite o ano de publicação: ", scan);
        int numeroPaginas = Input.scanInt(">> Digite o número de páginas: ", scan);
        int tipo = Input.scanInt("Qual tipo de Livro (1 - Físico, 2 - Digital): ", scan);

        Livro livro;
        if (tipo == 1) {
            int numeroExemplares = Input.scanInt(">> Informe o número de exemplares: ", scan);
            String dimensoes = Input.scanString(">> Informe as dimensões: ", scan);
            livro = new LivroFisico(titulo, autor, anoPublicacao, numeroPaginas, numeroExemplares, dimensoes);
        } else {
            double tamanhoArquivo = Input.scanPositiveDouble(">> Informe o tamanho do arquivo (MB): ", scan);
            String formato = Input.scanString(">> Informe o formato (PDF, EPUB, etc.): ", scan);
            livro = new LivroDigital(titulo, autor, anoPublicacao, numeroPaginas, formato, tamanhoArquivo);
        }

        try {
            biblioteca.adicionar(livro);
            System.out.println("Livro adicionado com sucesso!!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarAcervo() {
        var acervo = biblioteca.pesquisar();
        imprimirLista(acervo);
    }

    private static void pesquisarLivro() {
        String titulo = Input.scanString(">> Digite o título que procuras: ", scan);
        boolean pesquisaAutor = Input.scanYesNo(">> Deseja pesquisar por autor? (S/N): ", scan);
        List<Livro> livros;
        if (pesquisaAutor) {
            String autor = Input.scanString(">> Digite o nome do autor: ", scan);
            livros = biblioteca.pesquisar(titulo, autor);
        } else {
            livros = biblioteca.pesquisar(titulo);
        }
        imprimirLista(livros);
    }

    private static void imprimirLista(List<Livro> acervo) {
        if (acervo == null || acervo.isEmpty()) {
            System.out.println("Nenhum livro encontrado :(");
        } else {
            System.out.println("+------ Lista de Livros ------+");
            for (int i = 0; i < acervo.size(); i++) {
                System.out.println("[" + (i + 1) + "] " + acervo.get(i));
            }
        }
    }

    private static void removerLivro() {
        listarAcervo();
        int indice = Input.scanInt(">> Digite o índice do livro a remover: ", scan) - 1;
        try {
            Livro removido = biblioteca.remover(indice);
            System.out.println("| Livro removido: " + removido);
        } catch (Exception e) {
            System.out.println("| Erro: " + e.getMessage());
        }
    }

    private static void atualizarLivro() {
        listarAcervo();
        int indice = Input.scanInt("| Digite o índice do livro a atualizar: ", scan) - 1;

        String titulo = Input.scanString(">> Digite o Título: ", scan);
        String autor = Input.scanString(">> Digite o Autor: ", scan);
        int anoPublicacao = Input.scanInt(">> Digite o ano de publicação: ", scan);
        int numeroPaginas = Input.scanInt(">> Digite o número de páginas: ", scan);
        int tipo = Input.scanInt("Qual tipo de Livro (1 - Físico, 2 - Digital): ", scan);

        Livro novoLivro;
        if (tipo == 1) {
            int numeroExemplares = Input.scanInt(">> Informe o número de exemplares: ", scan);
            String dimensoes = Input.scanString(">> Informe as dimensões (cm): ", scan);
            novoLivro = new LivroFisico(titulo, autor, anoPublicacao, numeroPaginas, numeroExemplares, dimensoes);
        } else {
            double tamanhoArquivo = Input.scanPositiveDouble(">> Informe o tamanho do arquivo (MB): ", scan);
            String formato = Input.scanString(">> Informe o formato (PDF, EPUB, etc.): ", scan);
            novoLivro = new LivroDigital(titulo, autor, anoPublicacao, numeroPaginas, formato, tamanhoArquivo);
        }

        try {
            biblioteca.atualizar(indice, novoLivro);
            System.out.println("Livro atualizado com sucesso! :)");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void contarLivros() {
        int total = biblioteca.contarLivros();
        System.out.println("| Quantidade de livros no acervo: " + total);
    }

    private static void pesquisarAno() {
        int inicio = Input.scanInt(">> Digite o ano inicial: ", scan);
        int fim = Input.scanInt(">> Digite o ano final: ", scan);
        var livros = biblioteca.pesquisarAno(inicio, fim);
        imprimirLista(livros);
    }

    private static void antigoENovo() {
        System.out.println("| Livro mais antigo: " + biblioteca.maisAntigo());
        System.out.println("| Livro mais novo: " + biblioteca.maisNovo());
    }

    private static void pausar() {
        System.out.println("Pressione Enter para continuar...");
        scan.nextLine();
    }
}