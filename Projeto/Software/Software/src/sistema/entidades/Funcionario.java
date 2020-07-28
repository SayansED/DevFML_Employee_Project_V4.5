package sistema.entidades;

public class Funcionario {
    // variável destinado ao id do funcionário
    private int funcionarioId; 
    // variável destinado ao nome do funcionário
    private String funcionarioNome; 
    // variável destinado ao sobrenome do funcionário
    private String funcionarioSobrenome; 
    // variável destinado a data de nascimento do funcionário
    private String funcionarioDataNascimento;
    // variável destinado ao email do funcionário
    private String funcionarioEmail;
    // variável destinado ao cargo do funcionário
    private String funcionarioCargo; 
    // variável destinado ao salário atual do funcionário
    private Double funcionarioSalario;
    // variável destinado a entrada no sistema
    private String funcionarioDataEntradaSistema;

    public int getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(int id) {
        this.funcionarioId = id;
    }

    public String getFuncionarioNome() {
        return funcionarioNome;
    }

    public void setFuncionarioNome(String nome) {
        this.funcionarioNome = nome;
    }

    public String getFuncionarioSobrenome() {
        return funcionarioSobrenome;
    }

    public void setFuncionarioSobrenome(String sobrenome) {
        this.funcionarioSobrenome = sobrenome;
    }

    public String getFuncionarioDataNascimento() {
        return funcionarioDataNascimento;
    }

    public void setFuncionarioDataNascimento(String dataNascimento) {
        this.funcionarioDataNascimento = dataNascimento;
    }

    public String getFuncionarioEmail() {
        return funcionarioEmail;
    }

    public void setFuncionarioEmail(String email) {
        this.funcionarioEmail = email;
    }

    public String getFuncionarioCargo() {
        return funcionarioCargo;
    }

    public void setFuncionarioCargo(String cargo) {
        this.funcionarioCargo = cargo;
    }

    public Double getFuncionarioSalario() {
        return funcionarioSalario;
    }

    public void setFuncionarioSalario(Double salario) {
        this.funcionarioSalario = salario;
    }
    
    public String getFuncionarioDataEntradaSistema() {
    	return funcionarioDataEntradaSistema;
    }
    
    public void setFuncionarioDataEntradaSistema(String dataEntradaSistema) {
    	this.funcionarioDataEntradaSistema = dataEntradaSistema;
    }
    
    @Override
    public String toString() {
        return this.funcionarioNome + " " + this.funcionarioSobrenome;
    }    
}
