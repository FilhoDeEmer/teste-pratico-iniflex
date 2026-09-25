package br.com.iniflex.testepratico;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Principal {
    private static final DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final Locale LOCALE_BR = new Locale("pt", "BR");
    private static final BigDecimal SALARIO_MINIMO = new BigDecimal("1212.00");

    public static void main(String[] args) {
        //3.1
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000,10,18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990,5,12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961,5,2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988,10,14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995,1,5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999,11,19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993,3,31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994,7,8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003,5,24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996,9,2), new BigDecimal("2799.93"), "Gerente"));

        //3.2
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        //3.3
        System.out.println("Lista de funcionários:");
        funcionarios.forEach(Principal::imprimirFuncionario);

        //3.4
        funcionarios.forEach(f -> f.setSalario( f.getSalario().multiply(new BigDecimal("1.10")).setScale(2, RoundingMode.HALF_UP)));

        System.out.println("Funcionários com aumento de 10% no salário:");
        funcionarios.forEach(Principal::imprimirFuncionario);

        //3.5
        Map<String, List<Funcionario>> porFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));

        //3.6
        System.out.println("Funcionarios agrupados por função:");
        porFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println(" - " +f.getNome()));
        });

        //3.7
        System.out.println("Aniversariantes do mês de outubro e dezembro:");
        funcionarios.stream().filter(f -> f.getDataNascimento().getMonthValue() == 10 || f.getDataNascimento().getMonthValue() == 12).forEach(f -> System.out.println(f.getNome() + " - " + f.getDataNascimento().format(FORMATADOR_DATA)));

        //3.8
        Funcionario maisVelho = funcionarios.stream().min(Comparator.comparing(Funcionario::getDataNascimento)).orElseThrow();
        int idade = Period.between(maisVelho.getDataNascimento(), LocalDate.now()).getYears();
        System.out.println("Funcionário mais velho:");
        System.out.println("Nome: " + maisVelho.getNome() + " | Idade: " + idade);

        //3.9
        List<Funcionario> ordemAlfabetica = funcionarios.stream().sorted(Comparator.comparing(Funcionario::getNome)).collect(Collectors.toList());
        System.out.println("Funcionários em ordem alfabética:");
        ordemAlfabetica.forEach(f -> System.out.println(f.getNome()));

        //3.10
        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários");
        System.out.println("Total: " + formatarValor(totalSalarios));


        //3.11
        System.out.println("Salário mínimo por funcionário");
        funcionarios.forEach(f -> {
            BigDecimal quantidadeSalarioMinimos = f.getSalario().divide(SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            System.out.println(f.getNome() + ": " + formatarValor(quantidadeSalarioMinimos) + " salários mínimos");
        });
    }

    private static void imprimirFuncionario(Funcionario f) {
        System.out.println(
                "Nome: " + f.getNome() + " | Data de Nascimento: " + f.getDataNascimento().format(FORMATADOR_DATA)
                        + " | Salário: " + formatarValor(f.getSalario()) + " | Função: " + f.getFuncao());
    }

    private static String formatarValor(BigDecimal valor) {
        java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance(LOCALE_BR);
        nf.setMinimumFractionDigits(2);
        nf.setMaximumFractionDigits(2);
        return nf.format(valor);
    }

}
