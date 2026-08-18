import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsuarioPrinter {
    private static final int BORDER_SIZE = 74;
    private static final String DEFAULT_BORDER = "=";
    private static final String DARK_BORDER = "#";
    private static final String LIGHT_BORDER = "-";
    private static final String INDENTATION = "                    ";
    private static final String HEADER_FORMAT = "| %-5s | %-20s | %-22s | %-14s |\n";
    private static final String ROW_FORMAT = "| %-5s | %-20s | %-22s | %-14s |\n";

    public record Usuario(Long id, String nome, String email, String cpf) {
    }

    public void print(List<Usuario> lista, boolean maskCpf, boolean alignRight, String theme) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("ERRO: Lista de usuários vazia ou nula.");
            return;
        }

        var borderChar = getBorderChar(theme);
        var sb = new StringBuilder();
        appendHeader(sb, borderChar);

        for (var usuario : lista) {
            if (usuario != null) {
                appendUsuario(sb, usuario, maskCpf);
            }

            appendBorder(sb, borderChar);
            printOutput(sb, alignRight);
        }
    }

    private String getBorderChar(String theme) {
        if (Objects.equals(theme, "DARK")) {
            return DARK_BORDER;
        }

        if (Objects.equals(theme, "LIGHT")) {
            return LIGHT_BORDER;
        }

        return DEFAULT_BORDER;
    }

    private void appendHeader(StringBuilder sb, String borderChar) {
        appendBorder(sb, borderChar);
        sb.append(String.format(HEADER_FORMAT, "ID", "NOME", "EMAIL", "CPF"));
        appendBorder(sb, borderChar);
    }

    private void appendBorder(StringBuilder sb, String borderChar) {
        sb.append(borderChar.repeat(BORDER_SIZE)).append("\n");
    }

    private void appendUsuario(StringBuilder sb, Usuario usuario, boolean maskCpf) {
        sb.append(String.format(
                ROW_FORMAT,
                formatId(usuario.id()),
                formatNome(usuario.nome()),
                formatEmail(usuario.email()),
                formatCpf(usuario.cpf(), maskCpf)
        ));
    }

    private String formatId(Long id) {
        return id != null ? id.toString() : "0";
    }

    private String formatNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return "NÃO INFORMADO";
        }

        if (nome.length() > 20) {
            return nome.substring(0, 17) + "...";
        }

        return nome;
    }

    private String formatEmail(String email) {
        if (email == null || !email.contains("@")) {
            return "INVALIDO";
        }

        return email;
    }

    private String formatCpf(String cpf, boolean maskCpf) {
        if (cpf == null || cpf.length() != 11) {
            return "CPF INVALIDO";
        }

        if (maskCpf) {
            return "***." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-**";
        }

        return cpf.substring(0, 3) + "."
                + cpf.substring(3, 6) + "."
                + cpf.substring(6, 9) + "-"
                + cpf.substring(9, 11);
    }

    private void printOutput(StringBuilder sb, boolean alignRight) {
        if (!alignRight) {
            System.out.print(sb);
            return;
        }

        var lines = sb.toString().split("\n");
        for (var line : lines) {
            System.out.println(INDENTATION + line);
        }
    }

    public static void main(String[] args) {
        var usuarios = new ArrayList<Usuario>();
        usuarios.add(new Usuario(101L, "Carlos Eduardo de Souza", "carlos.souza@email.com", "12345678901"));
        usuarios.add(new Usuario(102L, "Ana Maria Silva", "ana.silva@email.com", "98765432100"));
        usuarios.add(new Usuario(103L, "João Pedro de Alcântara Bragança", "joao.pedro@email.com", "45678912345"));
        usuarios.add(new Usuario(104L, "Mariana Costa", "marianacosta.email.com", "11122233344"));
        usuarios.add(new Usuario(105L, "Lucas Mendes", "lucas@email.com", "12345"));
        usuarios.add(new Usuario(106L, "", "beatriz@email.com", "55566677788"));

        var printer = new UsuarioPrinter();
        printer.print(usuarios, true, true, "LIGHT");
    }
}
