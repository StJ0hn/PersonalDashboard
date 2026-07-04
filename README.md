# Personal Dashboard — Gerenciador de Tarefas e Estudos

> **Status:** Em desenvolvimento ativo (Iteração contínua em paralelo ao MOOC.fi)

Aplicação desktop construída para o gerenciamento de tarefas diárias e metas de estudo. 

## Objetivo
O projeto atua como um laboratório prático iterativo associado ao curso Java Programming da Universidade de Helsinque. A proposta arquitetural é evoluir o software progressivamente: iniciando como uma aplicação simples de terminal e escalando para uma interface gráfica estruturada, garantindo que cada novo conceito da linguagem (como Stream API, persistência em arquivos e MVC) seja fixado através da implementação real.

## Stack Tecnológico
* Java 21
* JavaFX
* Git & GitHub

## Arquitetura e Funcionalidades Principais
O sistema está sendo desenhado para suportar três pilares principais de gerenciamento:
* Gerenciamento de Tarefas: Operações de CRUD com níveis de prioridade, categorização e controle de prazos.
* Metas de Estudo: Definição de horas-alvo e registro detalhado de sessões.
* Analytics (Dashboard): Processamento de dados em memória para exibição de estatísticas (horas por área, tarefas pendentes/concluídas e streaks de produtividade).
* Persistência de Dados: Salvamento do estado da aplicação com exportação para formato CSV.

## Roadmap e Status de Desenvolvimento
O avanço do projeto acompanha os módulos do curso.

- [x] Menu no terminal e CRUD básico de tarefas.
- [x] Modelagem das classes de domínio (Task, Goal, StudySession).
- [x] Aplicação de herança, interfaces e Enums (Priority).
- [x] Estruturação em arquitetura MVC.
- [ ] Geração de estatísticas (HashMap e busca recursiva).
- [x] Refatoração e filtragem de dados com Stream API e tratamento de exceções.
- [ ] Implementação de persistência em arquivos (JSON e CSV export).
- [ ] Desenvolvimento da interface gráfica utilizando JavaFX.
- [ ] Cobertura de testes unitários com JUnit.

## Como Executar Localmente

1. Clone o repositório:
git clone https://github.com/StJ0hn/personal-dashboard.git

2. Acesse a pasta do projeto e abra em sua IDE de preferência (ex: IntelliJ IDEA).

3. Compile e execute a classe principal:
Main.java (A rotina de execução via terminal será atualizada para Maven/Gradle conforme a inserção do JavaFX).

## Desafios Técnicos e Aprendizados
1. Projetar Casos de usos e fluxos alternativos de interação com o Menu
2. Implementar lógica provisória de armazenamento de dados em Arrays e Arraylists
