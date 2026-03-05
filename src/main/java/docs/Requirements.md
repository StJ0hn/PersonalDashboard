# ☕ Personal Dashboard — Gerenciador de Estudos e Tarefas

> Documento de Requisitos & Roteiro de Implementação  
> Projeto prático paralelo ao curso **MOOC.fi — Java Programming**  
> Engenharia de Software — 5º/6º Período

---

## 1. Visão Geral do Projeto

| Campo | Valor |
|---|---|
| **Nome** | Personal Dashboard — Gerenciador de Estudos e Tarefas |
| **Linguagem** | Java 17+ |
| **Interface** | JavaFX (Desktop) |
| **Persistência** | Arquivo JSON / CSV (sem banco externo) |
| **Curso de referência** | MOOC.fi — Java Programming (Parts 1–14) |
| **Objetivo** | Fixar o conteúdo de cada Part aplicando na prática |
| **Resultado esperado** | App desktop funcional + portfólio para estágio/júnior |

### Propósito

O projeto acompanha o progresso no MOOC.fi de forma incremental: ao final de cada Part do curso, uma nova funcionalidade é adicionada ao app. Isso garante que os conceitos são fixados na prática imediatamente após o estudo teórico.

O app é útil para o próprio estudante, servindo como ferramenta de organização de tarefas e metas de estudo — o que aumenta a motivação para continuar desenvolvendo.

### Visão do Produto Final

Ao terminar o MOOC.fi, o app entregará:

- Cadastro e gerenciamento de tarefas diárias e metas de estudo
- Interface gráfica moderna em JavaFX com navegação por abas
- Dashboard visual com estatísticas de produtividade (horas, tarefas concluídas)
- Persistência de dados: tudo salvo em arquivo, nada se perde ao fechar o app
- Filtros, ordenação e busca de tarefas por categoria, prioridade e status
- Arquitetura MVC organizada — padrão usado no mercado profissional

---

## 2. Requisitos Funcionais

### 2.1 Gerenciamento de Tarefas

| ID | Requisito | Descrição |
|---|---|---|
| RF01 | Criar tarefa | Informar: título, descrição, categoria, prioridade (Alta/Média/Baixa) e data limite |
| RF02 | Listar tarefas | Exibir todas com status, prioridade e data limite visíveis |
| RF03 | Editar tarefa | Permitir alteração de qualquer campo de uma tarefa existente |
| RF04 | Concluir tarefa | Marcar como concluída; registrar data/hora da conclusão |
| RF05 | Excluir tarefa | Remover com confirmação do usuário |
| RF06 | Filtrar tarefas | Filtrar por status, categoria e prioridade |
| RF07 | Buscar tarefas | Busca textual por título ou descrição |

### 2.2 Metas de Estudo

- **RF08** — Criar meta: título, área (ex: Java, Nuvem), horas-alvo e prazo
- **RF09** — Registrar sessão de estudo: data, duração em minutos, anotação livre
- **RF10** — Visualizar progresso: horas acumuladas vs. horas-alvo por meta
- **RF11** — Histórico de sessões: listar todas as sessões de uma meta

### 2.3 Dashboard / Estatísticas

- **RF12** — Total de tarefas: pendentes, concluídas e atrasadas no período
- **RF13** — Horas de estudo: total semanal e mensal por área
- **RF14** — Gráfico simples de barras: tarefas por dia da semana
- **RF15** — Indicador de streak: quantos dias consecutivos com estudo registrado

### 2.4 Persistência de Dados

- **RF16** — Salvar automaticamente ao fechar o app
- **RF17** — Carregar dados ao abrir o app
- **RF18** — Exportar tarefas para arquivo CSV

---

## 3. Requisitos Não Funcionais

| Categoria | Requisito |
|---|---|
| **Usabilidade** | Interface intuitiva; ações principais acessíveis com no máximo 2 cliques |
| **Desempenho** | App inicia em menos de 3s; operações de CRUD em menos de 1s |
| **Manutenibilidade** | Código organizado em pacotes (model, view, controller, service, util) |
| **Portabilidade** | Deve rodar em Windows, Linux e macOS com JDK 17+ |
| **Confiabilidade** | Dados não devem ser perdidos em caso de fechamento abrupto |
| **Legibilidade** | Nomes de variáveis, métodos e classes em inglês; comentários em português |

---

## 4. Arquitetura e Estrutura do Projeto

### 4.1 Padrão Arquitetural: MVC

O projeto adota o padrão **Model-View-Controller (MVC)** a partir da refatoração da Part 7. Esse é o mesmo padrão utilizado em frameworks como Spring MVC — adotá-lo aqui prepara o terreno para o próximo passo da carreira.

```
View  →  Controller  →  Service  →  Model
                ↑                      ↓
           (eventos)            (dados/domínio)
```

### 4.2 Estrutura de Pacotes (final do curso)

```
src/
└── com/dashboard/
    ├── model/          # Classes de domínio: Task, Goal, StudySession, Priority (enum)
    ├── view/           # Telas JavaFX: MainView, TaskView, GoalView, DashboardView
    ├── controller/     # Lógica de apresentação; conecta View e Service
    ├── service/        # Regras de negócio: TaskService, GoalService, StatisticsService
    ├── repository/     # Leitura e escrita de dados em arquivo (JSON/CSV)
    └── util/           # Utilitários: DateFormatter, FileHelper, Validator
```

---

## 5. Roteiro de Implementação por Part

> **Como usar:** estude a Part, faça os exercícios do MOOC.fi, depois volte aqui e implemente os itens do checklist correspondente.

---

### 🟢 Parts 1 e 2 — Fundamentos: Menu no Terminal

**Conceitos do curso:** variáveis, condicionais, loops, métodos, leitura de input

**O que implementar:**

- [ ] Criar o projeto no IntelliJ com estrutura de pastas organizada
- [ ] Implementar um menu de texto no terminal com as opções: `[1] Adicionar tarefa  [2] Listar tarefas  [3] Sair`
- [ ] Criar uma tarefa com os campos: `título` (String), `prioridade` (String: Alta/Média/Baixa) e `concluída` (boolean)
- [ ] Armazenar tarefas em um array fixo de tamanho 50 (ArrayList vem na próxima Part)
- [ ] Método `imprimirTarefas()` que percorre e exibe todas as tarefas cadastradas
- [ ] Validar entrada: não aceitar título vazio
- [ ] Criar o repositório no GitHub e fazer o primeiro commit

> **Conexão com o curso:** o menu usa `while(true)` + `switch` — exatamente o que as Parts 1-2 ensinam. O método `imprimirTarefas()` pratica a criação e chamada de métodos com Scanner para input.

---

### 🔵 Parts 3 e 4 — Listas e Orientação a Objetos

**Conceitos do curso:** ArrayList, classes, objetos, encapsulamento, construtores

**O que implementar:**

- [ ] Criar a classe `Task` com atributos privados: `id`, `title`, `description`, `priority`, `category`, `completed`, `createdAt`
- [ ] Implementar getters, setters e construtor completo em `Task`
- [ ] Substituir o array por `ArrayList<Task>`
- [ ] Criar a classe `Goal` com: `id`, `title`, `area`, `targetHours`, `accumulatedMinutes`
- [ ] Criar a classe `StudySession` com: `goalId`, `date`, `durationMinutes`, `note`
- [ ] Implementar `toString()` em `Task` e `Goal` para exibição no terminal
- [ ] Adicionar opção de registrar sessão de estudo no menu
- [ ] Commitar cada classe nova separadamente com mensagem descritiva

> **Conexão com o curso:** criação de classes, construtores e encapsulamento são o coração das Parts 3-4. `ArrayList<Task>` pratica o uso de listas tipadas e `toString()` usa sobrescrita de método de instância.

---

### 🟣 Part 5 — Herança, Interfaces e Polimorfismo

**Conceitos do curso:** herança, classes abstratas, interfaces, polimorfismo, enums

**O que implementar:**

- [ ] Criar interface `Prioritizable` com método `int getPriorityLevel()`
- [ ] Fazer `Task` implementar `Prioritizable` — Alta=3, Média=2, Baixa=1
- [ ] Criar classe abstrata `BaseItem` com campos comuns: `id`, `title`, `createdAt`
- [ ] Fazer `Task` e `Goal` estender `BaseItem`
- [ ] Criar subclasses: `StudyTask extends Task` (campo `subject`) e `PersonalTask extends Task` (campo `location`)
- [ ] Criar `enum Priority { HIGH, MEDIUM, LOW }` substituindo o campo String de prioridade
- [ ] Implementar `Comparator` para ordenar tarefas por prioridade no menu
- [ ] Exibir no terminal as tarefas ordenadas por prioridade decrescente

> **Conexão com o curso:** herança com `extends` e polimorfismo são o tema central da Part 5. A interface `Prioritizable` simula um contrato — conceito fundamental em Java. O enum `Priority` introduz tipos enumerados de forma prática.

---

### 🔴 Part 6 — Interface Gráfica com JavaFX

**Conceitos do curso:** JavaFX, Scene, Stage, eventos, layouts, FXML

**O que implementar:**

- [ ] Criar a janela principal (`MainView`) com `BorderPane` como layout raiz
- [ ] Adicionar `TabPane` com três abas: **Tarefas**, **Metas** e **Dashboard** (inicialmente vazio)
- [ ] Na aba Tarefas: `TableView<Task>` com colunas Título, Prioridade, Categoria, Status
- [ ] Botões: Nova Tarefa, Editar, Concluir, Excluir — com eventos `onClick` configurados
- [ ] Criar um `Dialog` (formulário) para adicionar/editar tarefa com `TextField` e `ComboBox`
- [ ] Na aba Metas: `ListView<Goal>` com nome e progresso (horas acumuladas / horas-alvo)
- [ ] Estilizar com CSS inline básico: cores de prioridade nas linhas da tabela
- [ ] Garantir que os dados criados nas Parts anteriores continuam funcionando — o Model não muda

> **Conexão com o curso:** `Stage` e `Scene` são introduzidos na Part 6 — você vai usar exatamente isso. `EventHandler` para botões é o principal exercício prático da Part. `TableView` e `ListView` são os componentes mais usados em apps JavaFX reais.

---

### 🔷 Part 7 — Programação em Larga Escala: MVC

**Conceitos do curso:** separação de responsabilidades, pacotes, boas práticas, Javadoc

**O que implementar:**

- [ ] Reorganizar todo o projeto nos pacotes definidos na seção 4.2 deste documento
- [ ] Criar `TaskService` com: `addTask()`, `removeTask()`, `completeTask()`, `getTasks()`, `filterByPriority()`
- [ ] Criar `GoalService` com: `addGoal()`, `addSession()`, `getProgress(goalId)`
- [ ] Views chamam controllers; controllers chamam services — nunca direto ao model
- [ ] Remover qualquer lógica de negócio que ainda esteja nas classes View
- [ ] Criar `TaskController` que recebe eventos da View e chama `TaskService`
- [ ] Documentar cada classe pública com Javadoc (`/** */`)
- [ ] Commit de refatoração com mensagem explicando a mudança arquitetural

> **Conexão com o curso:** Part 7 trata de separação de preocupações e organização de projetos maiores. A refatoração para MVC é a aplicação direta desse conceito. Javadoc é introduzido aqui — documentar o projeto fixa o hábito desde cedo.

---

### 🟠 Parts 8 e 9 — HashMap, Recursão e Estruturas Avançadas

**Conceitos do curso:** HashMap, recursão, algoritmos de busca e ordenação

**O que implementar:**

- [ ] Usar `HashMap<String, List<Task>>` para indexar tarefas por categoria
- [ ] Usar `HashMap<String, Integer>` para acumular horas de estudo por área no Dashboard
- [ ] Implementar busca de tarefas por título usando algoritmo recursivo (dividir a lista)
- [ ] Popular a aba Dashboard: exibir HashMap de horas por área como lista de progresso
- [ ] Adicionar estatísticas no Dashboard: total de tarefas pendentes, concluídas, atrasadas
- [ ] Implementar `StatisticsService` que calcula todas as métricas do Dashboard
- [ ] Exibir no Dashboard o top 3 de categorias com mais tarefas pendentes

> **Conexão com o curso:** `HashMap` é o foco da Part 8 — usar como índice de categorias é aplicação real e prática. A busca recursiva é o exercício direto da Part 9. `StatisticsService` consolida o padrão MVC com uma camada de serviço analítica.

---

### 🟩 Parts 10 e 11 — Streams, Lambdas e Exceções

**Conceitos do curso:** Stream API, lambdas, Optional, tratamento de exceções

**O que implementar:**

- [ ] Reescrever os filtros de `TaskService` usando Stream API (`filter`, `map`, `sorted`, `collect`)
- [ ] Usar `Comparator.comparing()` com lambda para ordenação por prioridade e data
- [ ] Usar `Optional<Task>` no retorno de busca por ID para evitar `NullPointerException`
- [ ] Adicionar `try-catch` em todas as operações de arquivo
- [ ] Criar exceção customizada: `TaskNotFoundException extends RuntimeException`
- [ ] Lançar `TaskNotFoundException` ao tentar editar/excluir ID inexistente
- [ ] Exibir mensagem amigável ao usuário na View quando exceção for capturada
- [ ] Implementar o indicador de streak com stream sobre datas das sessões de estudo

> **Conexão com o curso:** Stream API e lambdas são o grande destaque das Parts 10-11. `Optional` é apresentado nessa fase — usar como retorno de busca é o caso clássico. Exceções customizadas consolidam o tratamento de erros de forma profissional.

---

### ⬛ Parts 12 a 14 — I/O de Arquivos, Polimento e Testes

**Conceitos do curso:** File I/O, serialização, testes unitários, tópicos finais

**O que implementar:**

- [ ] Implementar `TaskRepository`: salvar e carregar `List<Task>` em arquivo JSON (Gson ou Jackson)
- [ ] Implementar `GoalRepository`: salvar e carregar metas e sessões
- [ ] Salvar automaticamente ao fechar a janela (evento `onCloseRequest` da Stage)
- [ ] Carregar dados ao iniciar o app — tratar arquivo inexistente com lista vazia
- [ ] Implementar exportação de tarefas para CSV com `BufferedWriter`
- [ ] Adicionar gráfico de barras no Dashboard usando `BarChart` do JavaFX com dados reais
- [ ] Escrever pelo menos 5 testes unitários com **JUnit 5** (`TaskService` e `StatisticsService`)
- [ ] Polir a interface: ícones nos botões, cores consistentes, tela de boas-vindas
- [ ] Escrever `README.md` no GitHub com: descrição, screenshots, como executar e tecnologias usadas

> **Conexão com o curso:** File I/O é o tema central das partes finais — salvar/carregar dados é a aplicação direta. Testes unitários são introduzidos no final do curso — JUnit 5 é o padrão do mercado. O README bem escrito é o cartão de visita do projeto para recrutadores.

---

## 6. Checklist Geral de Progresso

Use como revisão rápida ao final de cada fase. Se todos os itens estiverem marcados, você está pronto para avançar.

### Fundação — Parts 1-2
- [ ] Projeto criado no IntelliJ com estrutura de pastas
- [ ] Menu funcional no terminal
- [ ] CRUD básico de tarefas no terminal
- [ ] Repositório no GitHub criado com primeiro commit

### Orientação a Objetos — Parts 3-5
- [ ] Classes `Task`, `Goal` e `StudySession` criadas com encapsulamento
- [ ] Enum `Priority` implementado
- [ ] Herança com `BaseItem` e subclasses funcionando
- [ ] Interface `Prioritizable` implementada
- [ ] Ordenação por prioridade no terminal

### Interface Gráfica — Part 6
- [ ] Janela JavaFX abre sem erros
- [ ] TabPane com abas Tarefas e Metas
- [ ] TableView exibe as tarefas com todas as colunas
- [ ] Dialog de adicionar/editar tarefa funciona
- [ ] Botões com eventos funcionais

### Arquitetura MVC — Part 7
- [ ] Pacotes organizados conforme a estrutura definida na seção 4.2
- [ ] `TaskService` e `GoalService` criados e testados manualmente
- [ ] Views não contêm lógica de negócio
- [ ] Javadoc em todas as classes públicas

### Dados e Persistência — Parts 8-14
- [ ] HashMap usado para índice de categorias e estatísticas
- [ ] Stream API e lambdas em todos os filtros
- [ ] `Optional` em buscas por ID
- [ ] Exceção customizada `TaskNotFoundException`
- [ ] Dados salvos e carregados de arquivo JSON
- [ ] Exportação CSV funcionando
- [ ] BarChart no Dashboard com dados reais
- [ ] Testes JUnit cobrindo `TaskService`
- [ ] README.md completo no GitHub

---

## 7. Dicas de Processo e Próximos Passos

### Boas práticas durante o desenvolvimento

- Faça commit a cada funcionalidade concluída — não só ao final da Part inteira
- Use mensagens de commit descritivas: `feat: adiciona filtro por prioridade com Stream API`
- Se travar em algo, documente com um comentário `// TODO:` e avance — volte depois
- Uma funcionalidade por vez, testando antes de seguir para a próxima

### Após concluir o projeto — trilha Java → Nuvem → Segurança

| Próximo passo | Por quê |
|---|---|
| **Spring Boot** | Com MVC dominado, Spring Boot será natural — é o mesmo padrão |
| **Spring Data JPA** | Substitui o seu Repository de arquivo por banco de dados real (PostgreSQL) |
| **REST API** | Transforma o app em uma API consumível por um frontend |
| **Docker** | Empacota o app em container — primeiro passo para a Nuvem |
| **AWS Cloud Practitioner** | Certificação ideal após Spring Boot e Docker básicos |

### Como apresentar este projeto para estágios

- GitHub público com README bem escrito, prints da interface e instruções de execução
- Mencionar a evolução incremental — mostra disciplina e aprendizado contínuo
- Destacar a arquitetura MVC — é o que o recrutador técnico quer ver num portfólio júnior
- Se possível, gravar um vídeo de 2-3 minutos demonstrando o app funcionando

---

> 💡 **Lembre-se:** Consistência bate intensidade. 1 hora por dia, todos os dias, vale mais do que 10 horas num fim de semana e sumir durante a semana. Bons estudos! ☕