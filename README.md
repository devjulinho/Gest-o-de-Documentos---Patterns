# Sistema de Gestão de Documentos – Padrões de Projeto

## Descrição do Projeto

Este projeto implementa um sistema de gestão de documentos com foco em organização, extensibilidade e clareza arquitetural. O sistema permite criar, editar, assinar, proteger e classificar documentos de diferentes tipos, mantendo histórico de ações e regras específicas de processamento, sem acoplamento excessivo entre os componentes.

<br>

---

## Padrões de Projeto Implementados

### 1. Command Pattern (Padrão Comando)

#### Justificativa

O padrão Command foi utilizado para separar claramente as ações executadas pelo sistema da interface que as dispara. Cada operação é tratada como um objeto independente, o que facilita o controle, o registro e a reversão das ações realizadas.

Essa abordagem permite a implementação de histórico, undo/redo e a adição de novas funcionalidades sem a necessidade de modificar código existente.

#### Participantes e Classes

| Classe                      | Papel no Padrão     | Descrição                                                                                   |
| --------------------------- | ------------------- | ------------------------------------------------------------------------------------------- |
| `Command`                   | **Command**         | Interface base que define os métodos `execute()`, `undo()`, `redo()` e `getDocumentoNovo()` |
| `AssinarDocumentoCommand`   | **ConcreteCommand** | Encapsula a ação de assinar um documento                                                    |
| `CriarDocumentoCommand`     | **ConcreteCommand** | Encapsula a criação de um documento                                                         |
| `SalvarDocumentoCommand`    | **ConcreteCommand** | Encapsula a edição e salvamento de documentos                                               |
| `TornarUrgenteCommand`      | **ConcreteCommand** | Marca um documento como urgente                                                             |
| `ProtegerDocumentoCommand`  | **ConcreteCommand** | Altera o nível de privacidade de um documento                                               |
| `CommandManager`            | **Invoker**         | Executa comandos e gerencia histórico (undo/redo)                                           |
| `LogCommand`                | **Auxiliary**       | Registra ações executadas no sistema                                                        |
| `GerenciadorDocumentoModel` | **Receiver**        | Mantém o estado dos documentos                                                              |
| `GestorDocumento`           | **Receiver**        | Executa as operações reais sobre os documentos                                              |

---

### 2. Macro Command Pattern (Padrão Comando Macro)

#### Justificativa

O padrão Macro Command foi aplicado para agrupar múltiplas operações em uma única ação lógica. Ele simplifica fluxos mais complexos do sistema e garante que operações compostas possam ser executadas e desfeitas como uma única unidade.

Essa solução promove reutilização de comandos existentes e mantém consistência no fluxo das ações.

#### Participantes e Classes

| Classe                    | Papel no Padrão          | Descrição                             |
| ------------------------- | ------------------------ | ------------------------------------- |
| `MacroCommand`            | **MacroCommand**         | Executa uma sequência de comandos     |
| `AlterarEAssinarMacro`    | **ConcreteMacroCommand** | Salva e assina um documento           |
| `PriorizarMacro`          | **ConcreteMacroCommand** | Torna um documento urgente e o assina |
| `SalvarDocumentoCommand`  | **ConcreteCommand**      | Comando reutilizado nos macros        |
| `AssinarDocumentoCommand` | **ConcreteCommand**      | Comando reutilizado nos macros        |
| `TornarUrgenteCommand`    | **ConcreteCommand**      | Comando reutilizado nos macros        |

---

### 3. Singleton Pattern (Padrão Singleton)

#### Justificativa

O padrão Singleton foi utilizado para garantir que o repositório de documentos possua uma única instância em toda a aplicação. Isso evita inconsistências de estado e centraliza o controle dos dados.

Essa decisão é essencial para manter a integridade do histórico e do estado dos documentos ao longo do uso do sistema.

#### Participantes e Classes

| Classe                      | Papel no Padrão | Descrição                                     |
| --------------------------- | --------------- | --------------------------------------------- |
| `GerenciadorDocumentoModel` | **Singleton**   | Mantém o estado global e único dos documentos |

---

### 4. Chain of Responsibility Pattern (Cadeia de Responsabilidade)

#### Justificativa

A Chain of Responsibility foi escolhida para resolver o problema de decidir qual regra de autenticação e geração de número de documento deve ser aplicada, sem recorrer a estruturas condicionais extensas e difíceis de manter.

O principal desafio desse padrão foi garantir uma ordem clara de processamento e um comportamento previsível quando nenhum handler específico fosse aplicável. Isso foi resolvido por meio de uma inicialização explícita da cadeia e da definição de um handler padrão que atua como fallback.

Essa abordagem mantém as regras desacopladas, facilita a adição de novos tipos de autenticação e torna o fluxo de decisão mais organizado e extensível.

#### Participantes e Classes

| Classe                          | Papel no Padrão                | Descrição                                                 |
| ------------------------------- | ------------------------------ | --------------------------------------------------------- |
| `AutenticadorHandler`           | **Handler**                    | Classe base que define o contrato da cadeia               |
| `AutenticadorCriminalHandler`   | **ConcreteHandler**            | Processa documentos criminais                             |
| `AutenticadorPessoalHandler`    | **ConcreteHandler**            | Processa documentos pessoais                              |
| `AutenticadorExportacaoHandler` | **ConcreteHandler**            | Processa documentos de exportação                         |
| `AutenticadorDefaultHandler`    | **ConcreteHandler (Fallback)** | Trata documentos não atendidos pelos handlers específicos |
| `Autenticador`                  | **Client**                     | Inicia a requisição na cadeia                             |
| `InicializadorChain`            | **Auxiliary**                  | Garante a inicialização ordenada da cadeia                |

---

## Autores

- **Luana Lima** – https://github.com/luad3cristal
- **Rafael Dantas** – https://github.com/RafaelD-S
- **Júlio Brito** – https://github.com/devjulinho

---

**Última atualização:** Fevereiro de 2026
