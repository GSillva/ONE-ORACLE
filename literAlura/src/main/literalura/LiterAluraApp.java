@SpringBootApplication
public class LiterAluraApp implements CommandLineRunner {
    private final GutendexService gutendexService;

    public LiterAluraApp(GutendexService gutendexService) {
        this.gutendexService = gutendexService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiterAluraApp.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n=== MENU LITERALURA ===");
            System.out.println("1. Buscar livro por título");
            System.out.println("2. Listar todos os livros");
            System.out.println("3. Listar autores");
            System.out.println("4. Listar livros por idioma");
            System.out.println("5. Sair");
            System.out.print("Escolha: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1 -> {
                    System.out.print("Título: ");
                    String title = sc.nextLine();
                    gutendexService.searchAndSaveBook(title);
                }
                case 2 -> gutendexService.listBooks();
                case 3 -> gutendexService.listAuthors();
                case 4 -> {
                    System.out.print("Idioma (ex: en, pt): ");
                    String lang = sc.nextLine();
                    gutendexService.listBooksByLanguage(lang);
                }
                case 5 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (option != 5);
    }
}
