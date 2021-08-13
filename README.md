# Aprendendo Spring Boot
#### Aqui vou estar documentando conceitos novos que estou aprendendo com o curso de Spring Boot da Alura.

---
# Table of contents <a name="topo"></a>
1. [@Controller](#controller)
2. [@RequestMapping](#requestmapping)
3. [O que é persistencia de dados?](#persistencia)
4. [@ResponseBody](#responsebody)
5. [DTO – Objeto de Transferencia de Dados](#dto)
6. [stream](#stream)
7. [Method References](#methodreference)
8. [Enum](#enum)
9. [REST (Representational State Transfer)](#rest)
	1. [Recursos](#recursos)
	2. [Identificador de recursos (URI)](#uri)
	3. [Manipulação de recursos (Verbos HTTP)](#verboshttp)
	4. [Respostas](#respostas)
10. [Spring Data JPA](#springdatajpa)
11. [@Entity](#entity)
12. [@GeneratedValue](#generatedvalue)
13. [@Enumerated](#enumerated)
	1. [Mapping Ordinal Value](#ordinal)
	2. [Mapping String Value](#string)
14. [JPA - Relacionamentos de Entidade](#relacionamentosentidade)
15. [Inserir Dados via data.sql H2](#sqlh2)
16. [DAO e Repository](#daorepo)
17. [Injeção de dependências com Spring](#di)
	1. [@Autowired](#autowired)
 
---

## @Controller <a name="controller"></a> 
#### [Voltar para o topo](#topo)

![](controller.png) 


Sua função é ser uma camada intermediária entre a camada de apresentação (View) e a camada de negócios (Model).
O controlador serve como um intermediário que organiza os eventos da interface com usuário e os direciona para a camada de modelo, assim, torna-se possível um reaproveitamento da camada de modelo em outros sistemas já que não existe dependência entre a visualização e o modelo.
Esta é uma classe de controlador (controller) simples que contém métodos para manipular solicitações HTTP para diferentes URLs.


Links para saber mais: 
- [Diferença entre @RestController e @Controller Annotation no Spring MVC e REST](https://medium.com/@gcbrandao/diferen%C3%A7a-entre-restcontroller-e-controller-annotation-no-spring-mvc-e-rest-8533998a93eb)
- [Top 10 Spring MVC and REST Annotations para programadores Java](https://medium.com/@gcbrandao/top-10-spring-mvc-and-rest-annotations-para-programadores-java-989735894f43)
- [Padrão MVC - Java Magazine](https://www.devmedia.com.br/padrao-mvc-java-magazine/21995#5)

## @RequestMapping <a name="requestmapping"></a>
#### [Voltar para o topo](#topo)

![](requestmapping.png) 

A classe Controller contém vários métodos manipuladores para manipular as diferentes solicitações HTTP, mas como o Spring mapeia uma solicitação específica para um método manipulador específico? Bem, isso é feito com a ajuda da annotation @RequestMapping. É uma annotation no nível do método que é colocada sobre um método.
Define a url que quando for requisitada chamara o metodo.

## O que é persistencia de dados? <a name="persistencia"></a>
#### [Voltar para o topo](#topo)

Podemos entender o que é persistência de dados como a garantia de que um dado foi salvo e que poderá ser recuperado quando necessário no futuro. Esse conceito existe na computação para referenciar o ato de salvar os dados. Mantendo o registro de dados processados pelos sistemas, e transformando-os em dados persistentes, geramos insumos para análises futuras que vão possibilitar a tomada de decisões estratégicas por empresas para alavancar seus negócios. O mais usual é utilizar um banco de dados, mas nada impede que sejam utilizadas outras ferramentas, como uma planilha no Excel, um arquivo de texto, etc. Tudo depende do propósito dos dados persistentes e de seu contexto.
No entanto, caso seja necessário fazer uma boa análise de dados, é interessante que os dados estejam armazenados em locais que te darão maior poder de recuperação e organização, por isso a recomendação de utilizar um bom banco de dados.

Uma dificuldade que temos de enfrentar é que, apesar da padronização do SQL, cada banco tem suas peculiaridades, com instruções próprias e tipos de colunas diferentes, o que pode tornar bastante trabalhoso migrar de um banco para outro, dado que sua aplicação já está específica para um determinado banco.

Visando diminuir essa dificuldade de transição entre os bancos de dados, temos a JPA. Ela é uma API baseada no conceito de ORM (Object-Relational Mapping ou Mapeamento Objeto-Relacional, onde as tabelas do banco de dados são representadas através de classes e os registros de cada tabela são representados como instâncias das classes correspondentes.) e funciona como uma camada de abstração entre a aplicação e o banco de dados, possibilitando uma maior independência entre os mesmos.

Com ORM, o desenvolvedor passa a usar, ao inves de comandos SQL, uma interface de programação, não sendo necessário uma correspondência direta entre tabelas do banco de dadoss e classes Java.

Para tal são necessários metadados para essa persistência. Esses metadados podem ser desenvolvidos utilizando-se arquivos XML, Java annotations ou ambos.

Com annotations, por exemplo, você poderá ter em sua classe indicadores de que aquela classe esta sofrendo persistência (Entity) ou de que seus métodos são colunas de uma determinada tabela , como observado abaixo, nesse exemplo:

![](exemploannotations.gif) 

Por default, o nome de uma tabela corresponde ao nome da classe, mas isto pode ser mudado com uso de @Table(name="MYNEWTABLE"). O mesmo acontece com as colunas, onde cada field sera mapeado com o nome de uma coluna. Tambem pode ser mudado utilizando-se @Column(name="mynewColumn”). O uso de @Id define que aquele  atributo está mapeado e corresponde à chave primária da tabela. Se usarmos juntamente a @GeneratedValue, teremos um valor gerado automaticamente.

E, se estamos falando de banco de dados, também teremos annotations para desmostrar o relacionamento, como @OneToOne,  @OneToMany, @ManyToOne e @ManyToMany.


Links para saber mais: 
- [Entenda o que é persistência de dados](https://www.javaavancado.com/entenda-o-que-e-persistencia-de-dados/)
- [Persistência de dados: tudo que você precisa saber sobre conceito, tipos e técnicas](https://www.take.net/blog/tecnologia/persistencia-de-dados/?unapproved=27597&moderation-hash=614738c1d4e3643a579952aa8c2ec3c8#comment-27597)
- [Persistindo dados em Java com JPA](https://www.devmedia.com.br/persistindo-dados-em-java-com-jpa/29797)
- [Persistência em Java com API JPA](http://www.linhadecodigo.com.br/artigo/2525/persistencia-em-java-com-api-jpa.aspx)


## @ResponseBody <a name="responsebody"></a>
#### [Voltar para o topo](#topo)

![](responsebody.png) 

Essa annotation é usada para transformar um objeto Java retornado do controller em uma representação de recurso solicitada por um cliente REST. Precisamos anotar cada método que gera resposta REST com a anotação @ResponseBody.

## DTO – Objeto de Transferencia de Dados <a name="dto"></a>
#### [Voltar para o topo](#topo)

Padrão Objeto de Transferência de Dados (do inglês, Data transfer object design pattern, ou simplesmente DTO) é um padrão de arquitetura de objetos que agregam e encapsulam dados para transferência.

Diferente do que ocorre com os objetos de negócio e os objetos de acesso a dados (DAO), o DTO não possui qualquer tipo de comportamento. A sua função é obter e armazenar dados. Quando estamos trabalhando com uma interface remota, cada chamada ao servidor pode custar muito tempo de processamento, a depender da quantidade de dados. Com o DTO, podemos filtrar quais dados serão transmitidos e assim reduzir esse problema.

O DTO é bastante utilizado também quando não queremos expor todos os dados da nossa camada de persistência mas precisamos exibir ao nosso cliente estes mesmos dados.

Links para saber mais:
- [Design Patterns - DTO](https://javabahia.github.io/falando-sobre-dto/)


## stream <a name="stream"></a>
#### [Voltar para o topo](#topo)

A Streams API traz uma nova opção para a manipulação de coleções em Java seguindo os princípios da programação funcional. Combinada com as expressões lambda, ela proporciona uma forma diferente de lidar com conjuntos de elementos, oferecendo ao desenvolvedor uma maneira simples e concisa de escrever código que resulta em facilidade de manutenção e paralelização sem efeitos indesejados em tempo de execução.

A paralelização de operações consiste basicamente em dividir uma tarefa maior em subtarefas menores, processar essas subtarefas em paralelo e, em seguida, combinar os resultados para obter o resultado final. Em sua estrutura, a API de Streams fornece um mecanismo similar para trabalhar com a Java Collections, convertendo a coleção em uma stream, em um primeiro momento, processando os vários elementos em paralelo em seguida e, por fim, reunindo os elementos resultantes em uma coleção.

Exemplo: encontrar em uma lista de ordens de serviço todas aquelas que estão relacionadas à ativação, por exemplo, de um serviço de telefonia, e por fim retornar todos os identificadores dessas ordens classificados de maneira decrescente segundo o valor cobrado pelo serviço.

Antes do stream:
````
List<Ordem> OrdensAtivacao = new Arraylist<>();
for(Ordem o: ordens){    
  if(o.getType() == Ordem.ATIVACAO) {  
    OrdensAtivacao.add(t); 
    } 
  }
Collections.sort(OrdensAtivacao, new Comparator(){ 
  public int compare(Ordem t1, Ordem t2){          
    return t2.getValue().compareTo(t1.getValue());      
  }
});
List<Integer> ordensIDs = new ArrayList<>();
for(Ordem o: OrdensAtivacao){      
  ordensIDs.add(t.getId());
}
````

Depois do stream:
````
List<Integer> ordensIDs =    Ordem.stream()
               .filter(o -> o.getType() == Ordem.ATIVACAO)
               .sorted(comparing(Ordem::getValue).reversed())
               .map(Ordem::getId)
               .collect(toList());
````

Primeiramente, obtemos uma stream da lista de dados usando o método stream(), da interface Collection. Depois, uma série de operações são aplicadas. O método filter(), por exemplo, retorna apenas as ordens que são do tipo ATIVACAO. Sua saída é processada pela operação sorted(), que ordenará as operações de forma decrescente levando em consideração o valor da operação. Em seguida, o resultado de sorted() será manipulado pelo método map(), que obterá todas as informações que desejamos, ou seja, todos os identificadores das ordens da lista de operações. Por fim, o método collect() devolve uma lista de inteiros, em oposição aos demais, que sempre retornam uma nova stream como resultado do processamento.

Em uma coleção é possível navegar até os elementos de diferentes maneiras, tanto de forma sequencial quanto por meio de índices. Já em uma Stream o acesso aos elementos é sequencial, não sendo possível alcançá-los através de índices, pois inexiste uma estrutura de dados para armazenar os elementos que, por sua vez, são processados sob demanda.
 
Outro diferencial é verificado quando se tem a necessidade de manipular grandes quantidades de dados. Nesses casos a Streams API oferece a possibilidade de trabalhar com esses dados de forma paralela, viabilizando uma melhora de desempenho ao tirar proveito do poder de processamento dos computadores modernos.

Tomando como exemplo o código acima, para que o desenvolvedor consiga fazer uso da paralelização, basta trocar o método stream() por parallelStream(). Dessa forma a Streams API irá decompor as ações em várias subtarefas, e as operações serão processadas em paralelo, explorando os recursos oferecidos pelos diversos núcleos do processador.

Em suma, a Streams API trabalha convertendo uma fonte de dados em uma Stream. Em seguida, realiza o processamento dos dados através das operações intermediárias e, por fim, retorna uma nova coleção ou valor reduzido (map-reduce) com a chamada a uma operação terminal.

Links para saber mais: 
- [Java 8: Iniciando o desenvolvimento com a Streams API](https://www.oracle.com/br/technical-resources/articles/java-stream-api.html)

## Method References <a name="methodreference"></a>
#### [Voltar para o topo](#topo)

You use lambda expressions to create anonymous methods. Sometimes, however, a lambda expression does nothing but call an existing method. In those cases, it's often clearer to refer to the existing method by name. Method references enable you to do this; they are compact, easy-to-read lambda expressions for methods that already have a name.

Kind | Syntax
--------- | ------
Reference to a static method | ContainingClass :: staticMethodName
Reference to an instance method of a particular object | containingObject :: instanceMethodName
Reference to an instance method of an arbitrary object of a particular type	    | ContainingType :: methodName	
Reference to a constructor	  | ClassName :: new	

````
public class MethodReferencesExamples {
    
    public static <T> T mergeThings(T a, T b, BiFunction<T, T, T> merger) {
        return merger.apply(a, b);
    }
    
    public static String appendStrings(String a, String b) {
        return a + b;
    }
    
    public String appendStrings2(String a, String b) {
        return a + b;
    }
````
````
MethodReferencesExamples myApp = new MethodReferencesExamples();

// Reference to a static method
MethodReferencesExamples::appendStrings

// Reference to an instance method of a particular object  
myApp::appendStrings2

// Reference to an instance method of an arbitrary object of a particular type
String::concat

// Reference to a constructor
HashSet::new
````

Links para saber mais: 
- [Method References](https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html)

## ENUM <a name="enum"></a>
#### [Voltar para o topo](#topo)

São tipos de campos que consistem em um conjunto fixo de constantes (static final), sendo como uma lista de valores pré-definidos. Na linguagem de programação Java, pode ser definido um tipo de enumeração usando a palavra chave enum.

Todos os tipos enums implicitamente estendem a classe java.lang.Enum, sendo que o Java não suporta herança múltipla, não podendo estender nenhuma outra classe.

Declaração de Enum, com construtor e métodos:

````
public enum OpcoesMenu {
SALVAR(1), IMPRMIR(2), ABRIR(3), VISUALIZAR(4), FECHAR(5);

private final int valor;
OpcoesMenu(int valorOpcao){
valor = valorOpcao;
}
public int getValor(){
return valor;
}
}
````

Links para saber mais:
- [Tipos Enum no Java](https://www.devmedia.com.br/tipos-enum-no-java/25729)

## REST (Representational State Transfer) <a name="rest"></a>
#### [Voltar para o topo](#topo)

Tem como objetivo primário a definição de características fundamentais para a construção de aplicações Web seguindo boas práticas.

### Recursos <a name="recursos"></a>
Um recurso é um elemento abstrato e que nos permite mapear qualquer coisa do mundo real como um elemento para acesso via Web.

Exemplo:
- Alunos
- Cursos

### Identificador de recursos (URI) <a name="uri"></a>

- Alunos (/alunos)
- Cursos (/cursos)

### Manipulação de recursos (Verbos HTTP) <a name="verboshttp"></a>

![](verboshttp.png) 

Para interagirmos com os recursos, o HTTP nos fornece uma interface de operações padronizadas, permitindo que possamos criar, atualizar, pesquisar, remover e executar operações sob um determinado recurso.

- GET: (/alunos) O método GET é utilizado quando existe a necessidade de se obter um recurso. Em caso de sucesso, retorna uma representação em JSON e um código de resposta HTTP de 200 (OK). Em caso de erro, ele geralmente retorna um 404 (NOT FOUND) ou 400 (BAD REQUEST). 

De acordo com o design da especificação HTTP, requisições GET (juntamente com HEAD) são usadas apenas para ler dados e jamais alterá-los. Portanto, quando usados dessa forma, são considerados seguros.
![](get.png) 
Get com parametro:
![](getcomparam1.png)

![](getcomparam2.png) 

- POST: (/alunos) Utilizamos o método POST quando desejamos criar algum recurso no servidor a partir de uma determinada representação. Na criação bem-sucedida, retornar o status HTTP 201. 

Ele não é um método seguro, pois altera o estado do recurso no servidor. Ele também não é idempotente, o que quer dizer que se ele for executado duas vezes de forma idêntica serão criados dois itens diferentes com o mesmo conjunto de dados.

![](post1.png) 

![](post2.png) 

- PUT: (/alunos/{id}) PUT é mais utilizado para substituir (ou atualizar) recursos, executando a requisição para uma URI de recurso conhecido, com o corpo da requisição contendo a representação recém-atualizada do recurso original.

Na atualização bem-sucedida, retorna 200 (ou 204 se não retornar qualquer conteúdo no corpo). Retornar os dados do recurso no corpo é opcional, lembrando que fazer isso causa maior consumo de banda.

PUT não é uma operação segura, pois modifica estado no servidor, mas é idempotente. Em outras palavras, se você atualizar um recurso usando PUT e, em seguida, fazer essa mesma chamada novamente, o recurso ainda está lá e ainda tem o mesmo estado.

![](put.png) 

- DELETE: (/alunos/{id}) Como você já deve estar imaginando, o método DELETE é utilizado com o intuito de remover um recurso em um determinado servidor. Na exclusão bem-sucedida, devolve o status HTTP 200 (OK) ou o status HTTP 204 (NO CONTENT) sem corpo de resposta.

Operações DELETE são idempotentes.

![](delete.png) 

### Respostas <a name="respostas"></a> 

Baseado nos métodos que discutimos, o servidor deve processar cada uma das requisições e retornar uma resposta adequada. Veja um resumo de cada uma dessas respostas.

1XX – Informações Gerais

2XX – Sucesso

3XX – Redirecionamento

4XX – Erro no cliente

5XX – Erro no servidor


Para saber mais:
- [HTTP: Verbos](https://www.devmedia.com.br/http-verbos/41224)
- [4 Conceitos sobre REST que Qualquer Desenvolvedor Precisa Conhecer](https://blog.algaworks.com/4-conceitos-sobre-rest-que-qualquer-desenvolvedor-precisa-conhecer/)
- [API Rest e os verbos HTTP](https://blog.mbeck.com.br/api-rest-e-os-verbos-http-46e189085e21)

## Spring Data JPA <a name="springdatajpa"></a>
#### [Voltar para o topo](#topo)

Adicionando no pom.xml:

````
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
````
(por padrão usa o Hibernate)

````
<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
</dependency>
````
O H2 é um banco de dados Open Source que funciona em memória com um console acessível pelo browser dentro do contexto da aplicação.

Adicionar em main/resources/application.properties:
````
# datasource 
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.url=jdbc:h2:mem:alura-forum
spring.datasource.username=sa
spring.datasource.password=

# jpa 
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# h2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
````

## @Entity <a name="entity"></a> 
#### [Voltar para o topo](#topo)

A anotação @Entity é utilizada para informar que uma classe também é uma entidade. A partir disso, a JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo nome no banco de dados, onde os dados de objetos desse tipo poderão ser persistidos.

Uma entidade representa, na Orientação a Objetos, uma tabela do banco de dados, e cada instância dessa entidade representa uma linha dessa tabela.

Exemplo:
````
@Entity
public class Produto {

}
````


Para saber mais: 
- [JPA: Como usar a anotação @Entity](https://www.devmedia.com.br/jpa-como-usar-a-anotacao-entity/38410)

## @GeneratedValue <a name="generatedvalue"></a> 
#### [Voltar para o topo](#topo)

A anotação @GeneratedValue é utilizada para informar que a geração do valor do identificador único da entidade será gerenciada pelo provedor de persistência. Essa anotação deve ser adicionada logo após a anotação @Id. Quando não anotamos o campo com essa opção, significa que a responsabilidade de gerar e gerenciar as chaves primárias será da aplicação, em outras palavras, do nosso código, como vemos no exemplo a seguir:

````
@Entity
public class Produto {

  @Id @GeneratedValue
  private long id;

}
````
Com o código deste exemplo, a JPA passará a utilizar a estratégia de geração de chave primária do banco de dados ao qual a aplicação está conectada.

Estratégia | Descrição
--------- | ------
GenerationType.AUTO | Valor padrão, deixa com o provedor de persistência a escolha da estratégia mais adequada de acordo com o banco de dados.
GenerationType.IDENTITY | Informamos ao provedor de persistência que os valores a serem atribuídos ao identificador único serão gerados pela coluna de auto incremento ([o que é auto incremento](http://www.bosontreinamentos.com.br/mysql/mysql-auto-incremento-de-valores-em-colunas-08/)) do banco de dados. Assim, um valor para o identificador é gerado para cada registro inserido no banco. Alguns bancos de dados podem não suportar essa opção.
GenerationType.SEQUENCE  | Informamos ao provedor de persistência que os valores serão gerados a partir de uma sequence ([o que é sequence](https://consultabd.wordpress.com/2019/11/22/sequence/)). Caso não seja especificado um nome para a sequence, será utilizada uma sequence padrão, a qual será global, para todas as entidades. Caso uma sequence seja especificada, o provedor passará a adotar essa sequence para criação das chaves primárias. Alguns bancos de dados podem não suportar essa opção.
GenerationType.TABLE  | Com a opção TABLE é necessário criar uma tabela para gerenciar as chaves primárias. Por causa da sobrecarga de consultas necessárias para manter a tabela atualizada, essa opção é pouco recomendada.

Exemplo:
````
@Entity
public class Produto {

@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String nome;

    //getters e setters omitidos

}
````

Para saber mais:
- [JPA: Como usar a anotação @GeneratedValue](https://www.devmedia.com.br/jpa-como-usar-a-anotacao-generatedvalue/38592)
- [Entendendo a geração de chaves com JPA](https://www.alura.com.br/artigos/entendendo-a-geracao-de-chaves-com-jpa)


## @Enumerated <a name="enumerated"></a> 
#### [Voltar para o topo](#topo)

The most common option to map an enum value to and from its database representation in JPA before 2.1. is to use the @Enumerated annotation. This way, we can instruct a JPA provider to convert an enum to its ordinal or String value.

### Mapping Ordinal Value <a name="ordinal"></a> 
If we put the @Enumerated(EnumType.ORDINAL) annotation on the enum field, JPA will use the Enum.ordinal() value when persisting a given entity in the database.

Let's introduce the first enum:
````
public enum Status {
    OPEN, REVIEW, APPROVED, REJECTED;
}
````
Next, let's add it to the Article class and annotate it with @Enumerated(EnumType.ORDINAL):
````
@Entity
public class Article {
    @Id
    private int id;

    private String title;

    @Enumerated(EnumType.ORDINAL)
    private Status status;
}
````
Now, when persisting an Article entity:
````
Article article = new Article();
article.setId(1);
article.setTitle("ordinal title");
article.setStatus(Status.OPEN);
````
JPA will trigger the following SQL statement:
````
insert 
into
    Article
    (status, title, id) 
values
    (?, ?, ?)
binding parameter [1] as [INTEGER] - [0]
binding parameter [2] as [VARCHAR] - [ordinal title]
binding parameter [3] as [INTEGER] - [1]
````
A problem with this kind of mapping arises when we need to modify our enum. If we add a new value in the middle or rearrange the enum's order, we'll break the existing data model.

Such issues might be hard to catch, as well as problematic to fix, as we would have to update all the database records.

### Mapping String Value <a name="string"></a> 

Analogously, JPA will use the Enum.name() value when storing an entity if we annotate the enum field with @Enumerated(EnumType.STRING).

Let's create the second enum:
````
public enum Type {
    INTERNAL, EXTERNAL;
}
````
And let's add it to our Article class and annotate it with @Enumerated(EnumType.STRING):
````
@Entity
public class Article {
    @Id
    private int id;

    private String title;

    @Enumerated(EnumType.ORDINAL)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Type type;
}
````
Now, when persisting an Article entity:
````
Article article = new Article();
article.setId(2);
article.setTitle("string title");
article.setType(Type.EXTERNAL);
````
JPA will execute the following SQL statement:
````
insert 
into
    Article
    (status, title, type, id) 
values
    (?, ?, ?, ?)
binding parameter [1] as [INTEGER] - [null]
binding parameter [2] as [VARCHAR] - [string title]
binding parameter [3] as [VARCHAR] - [EXTERNAL]
binding parameter [4] as [INTEGER] - [2]
````
With @Enumerated(EnumType.STRING), we can safely add new enum values or change our enum's order. However, renaming an enum value will still break the database data.

Additionally, even though this data representation is far more readable compared to the @Enumerated(EnumType.ORDINAL) option, it also consumes a lot more space than necessary. This might turn out to be a significant issue when we need to deal with a high volume of data.

Para saber mais:
- [Persisting Enums in JPA](https://www.baeldung.com/jpa-persisting-enums-in-jpa)
- [JPA and Enums via @Enumerated](https://tomee.apache.org/examples-trunk/jpa-enumerated/)

## JPA - Relacionamentos de Entidade <a name="relacionamentosentidade"></a>    
#### [Voltar para o topo](#topo)

### @OneToOne <a name="onetoone"></a> 
No relacionamento linear, um artigo pode ser ligado a somente outro um artigo. Significa que cada fileira de uma entidade está referida uma e somente uma fileiras de uma outra entidade.
Deixe-nos considerar o exemplo acima. Empregado e Departmento em uma maneira unidirecional reversa, a relação é relação linear. Significa que cada empregado pertence a somente um departamento.
 A entidade do departamento da classe é mostrada como segue:
````
package com.tutorialspoint.eclipselink.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Department 
{
   @Id 
   @GeneratedValue( strategy=GenerationType.AUTO )
   private int id;
   private String name;
   
   public int getId() 
   {
      return id;
   }
    
   public void setId(int id) 
   {
      this.id = id;
   }
    
   public String getName( )
   {
      return name;
   }
    
   public void setName( String deptName )
   {
      this.name = deptName;
   }
}
````
A classe da entidade do empregado é mostrada como segue:
````
package com.tutorialspoint.eclipselink.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Employee 
{
   @Id
   @GeneratedValue( strategy= GenerationType.AUTO ) 
   private int eid;
   private String ename;
   private double salary;
   private String deg;

   @OneToOne
   private Department department;

   public Employee(int eid, 
   String ename, double salary, String deg) 
   {
      super( );
      this.eid = eid;
      this.ename = ename;
      this.salary = salary;
      this.deg = deg;
   }

   public Employee( ) 
   {
      super();
   }

   public int getEid( ) 
   {
      return eid;
   }
   public void setEid(int eid)  
   {
      this.eid = eid;
   }

   public String getEname( ) 
   {
      return ename;
   }
   public void setEname(String ename) 
   {
      this.ename = ename;
   }

   public double getSalary( ) 
   {
      return salary;
   }
   public void setSalary(double salary) 
   {
      this.salary = salary;
   }

   public String getDeg( ) 
   {
      return deg;
   }
   public void setDeg(String deg) 
   {
      this.deg = deg;
   }

   public Department getDepartment() 
   {
      return department;
   }

   public void setDepartment(Department department) 
   {
      this.department = department;
   }
}
````
Resultado (existem passos intermediarios, para mais detalhes acesse o primeiro link em "para saber mais" logo abaixo):
````
Select * from department
````

ID | Nome
----|------
301 | Desenvolvimento

````
Select * from employee
````
Eid|	Deg|	Ename|	Salário|	Departamento_id
----|-----------|--------|------|----------------------
302|	Escritor técnico|	Satish|	45000|	301

### @ManyToOne <a name="manytoone"></a> 

# Assistir [Tutorial de mapeamento de relacionamentos com JPA - JPA LiveClass #15](https://www.youtube.com/watch?v=ODXvvEzXlQY&ab_channel=AlgaWorks) para entender melhor

Para saber mais:
- [JPA - Relacionamentos de Entidade](https://www.tutorialspoint.com/pg/jpa/jpa_entity_relationships.htm)
- [Como criar relacionamentos entre entidades com Hibernate](https://www.devmedia.com.br/como-criar-relacionamentos-entre-entidades-com-hibernate/33610)
- [Tutorial de mapeamento de relacionamentos com JPA - JPA LiveClass #15](https://www.youtube.com/watch?v=ODXvvEzXlQY&ab_channel=AlgaWorks)

## Inserir Dados via data.sql H2 <a name="sqlh2"></a>
#### [Voltar para o topo](#topo)

Para adicionar informações no banco de dados (para o H2), cria-se um arquivo `data.sql` em `main\resources`. 

No meu caso deu um erro que foi resolvido com esse link: [Erro ao Inserir Dados via data.sql H2](https://cursos.alura.com.br/forum/topico-erro-ao-inserir-dados-via-data-sql-h2-160604).

Erro explicado:
````
Hibernate and data.sql
By default, data.sql scripts are now run before Hibernate is initialized. This aligns the behavior of basic script-based 
initialization with that of Flyway and Liquibase. If you want to use data.sql to populate a schema created by Hibernate, 
set spring.jpa.defer-datasource-initialization to true. While mixing database initialization technologies is not 
recommended, this will also allow you to use a schema.sql script to build upon a Hibernate-created schema before 
it’s populated via data.sql.
````
Link: https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.5-Release-Notes#hibernate-and-datasql

Após inserir as informações em `data.sql` para popular o banco de dados e rodar o programa, acessar `localhost:8080/h2-console`. A seguinte tela irá aparecer no navegador:

![](h2-console.png)

Deve ser feita a modificação na url:

![](h2atualizaurl.png)

Esta url deve ser a mesma que está no arquivo `application.properties`:

![](urlemappproperties.png)

Após modificar, clicar em `salvar` e em `conectar`.


## DAO e Repository <a name="daorepo"></a> 
#### [Voltar para o topo](#topo)

O DAO e o Repository podem ser usados juntos, mas são padrões com funções diferentes.

O DAO é considerado um padrão de integração e é usado como parte da infraestrutura da aplicação. Um DAO serve para você especificar qual o banco de dados vai ser usado, como ele será usado e todas as instruções que devem ser passadas a ele. Ou seja, o DAO conhece e sabe exatamente com qual infraestrutura (banco de dados, arquivos, memória, ...) ele está lidando.

O Repository é considerado um padrão de domínio e faz parte das regras de negócios de uma aplicação. Esse padrão não tem conhecimento da infraestrutura, assim, ele não sabe que está lidando com um banco de dados. Sua real função é trabalhar como uma porta ou janela de acesso a outra camada, que poderia ser o DAO.
E o que ele faz? Apenas retorna um objeto de domínio, como um objeto Pessoa ou uma lista de objetos do tipo Pessoa. Como ele retorna isso, bem, isso é um segredo para ele, já que ele não sabe o que está acontecendo do outro lado da janela.

Antes do uso de frameworks ORM, como o Hibernate, o DAO era bastante usado em aplicações com acesso a banco de dados. Com a chegada dos frameworks ORM ele continuou sendo usado, mas na verdade os frameworks é que fazem o trabalho de DAO, porque são eles que conhecem e lidam com a infraestrutura.
Só que se continuou usando o nome DAO para adicionar as classes que continham as instruções de CRUD. Algo como um extensão do que os frameworks ofereciam. Essa pratica gerou algumas controvérsias.

Alguns assumem que sim, se pode chamar essas classes de DAO, porque se elas possuem os objetos de consulta a banco de dados e as instruções HQL, JPQL ou mesmo SQL, seria ainda infraestrutura. Porque são comandos específicos de banco de dados e banco de dados estão ligados a infraestrutura.

Outros defendem que essas classes, com as consultas e demais operações, seriam um repositório. Já que quem lida com a infraestrutura é o framework.
Mas, o repositório deveria ser apenas uma janela, não deveria saber que um HQL vai devolver um objeto Pessoa ou uma lista qualquer de pessoas. Por conta disso, não se chega realmente a uma conclusão.

Links para saber mais:
- https://www.devmedia.com.br/forum/dao-ou-repository-qual-usar/582418

## Injeção de dependências com Spring <a name="di"></a> 
#### [Voltar para o topo](#topo)

Injeção de dependências (ou Dependency Injection – DI) é um tipo de inversão de controle (ou Inversion of Control – IoC) que dá nome ao processo de prover instâncias de classes que um objeto precisa para funcionar.

Vamos supor uma classe como abaixo:
````
public class ClienteServico {
 
  private ClienteRepositorio repositorio;
 
  public void salvar(Cliente cliente) {
    this.repositorio.salvar(cliente);
  }
  ...
}
````
Como a propriedade repositorio é uma dependência de ClienteServico, então, segundo o conceito de injeção de dependências, uma instância dessa propriedade deve ser provida.

Para manter o conceito de injeção não podemos utilizar a palavra reservada new como abaixo:
````
public class ClienteServico {
 
  private ClienteRepositorio repositorio = new ClienteRepositorioJPA();
 
  public void salvar(Cliente cliente) {
    this.repositorio.salvar(cliente);
  }
 
  ...
}
````
Isso porque injeção é algo que espera-se que venha de fora para dentro.

A pergunta agora é: Por onde essa instância vai vir?

Isso vamos ver em um próximo tópico, sobre pontos de injeção.

O importante agora é entender que dessa forma nós conseguimos programar voltados para interfaces e, com isso, manter o baixo acoplamento entre as classes de um mesmo projeto.

Com certeza, essa característica é uma grande vantagem para a arquitetura do seu sistema. Sendo assim, esse é um conceito que merece a atenção da sua parte.

Pontos de injeção
Podemos considerar como pontos de injeção qualquer maneira que possibilite entregar a dependência para um objeto.

Os dois mais comuns são pelo construtor:
````
public class ClienteServico {
 
  private ClienteRepositorio repositorio;
 
  public ClienteServico(ClienteRepositorio repositorio) {
    this.repositorio = repositorio
  }
 
  ...
}
 
public class Main {
 
  public static void main(String... args) {
    ClienteRepositorio clienteRepositorio = new ClienteRepositorioJPA();
 
    ClienteServico clienteServico = new ClienteServico(clienteRepositorio);
    ...
  }
}
````
… e pelo método setter:
````
public class ClienteServico {
 
  private ClienteRepositorio repositorio;
 
  ...
 
  public void setClienteRepositorio(ClienteRepositorio repositorio) {
    this.repositorio = repositorio;
  }
}
 
public class Main {
 
  public static void main(String... args) {
    ClienteRepositorio clienteRepositorio = new ClienteRepositorioJPA();
 
    ClienteServico clienteServico = new ClienteServico();
    clienteServico.setClienteRepositorio(clienteRepositorio);
    ...
  }
}
````
### @Autowired <a name="autowired"></a> 

No Spring, para marcar os pontos de injeção dentro da sua classe, você utiliza a anotação @Autowired.

Você pode utilizar essa anotação em:

- Propriedades;
- Construtores; e
- Métodos (mais comumente, os setters)

Utilizando a anotação nas propriedades, seria:
````
public class ClienteServico {
   
  @Autowired
  private ClienteRepositorio repositorio;
   
  ...
}
````
No construtor:
````
public class ClienteServico {
   
  private ClienteRepositorio repositorio;
   
  @Autowired
  public ClienteServico(ClienteRepositorio repositorio) {
    this.repositorio = repositorio;
  }
 
  ...
}
````
Por último, com métodos, seria assim:
````
public class ClienteServico {
   
  private ClienteRepositorio repositorio;
   
  ...
 
  // Caso você preferisse, esse método poderia se 
  // chamar também "configurarRepositorio", mas
  // o mais comum é criar um método setter mesmo.
  @Autowired
  public void setRepositorio(ClienteRepositorio repositorio) {
    this.repositorio = repositorio;
  }
}
````
É possível também misturar. Você pode, por exemplo, marcar uma propriedade e um construtor:
````
public class ClienteServico {
 
  @Autowired
  private Notificador notificador;
   
  private ClienteRepositorio repositorio;
   
  @Autowired
  public ClienteServico(ClienteRepositorio repositorio) {
    this.repositorio = repositorio;
  }
 
  ...
}
````
Links para saber mais:
- [Injeção de dependências com Spring](https://blog.algaworks.com/injecao-de-dependencias-com-spring/)
- [Injeção de dependência e inversão de controle](https://www.youtube.com/watch?v=O07XFebgw-g&ab_channel=DevSuperior)
- [Inversão de Controle x Injeção de Dependência](https://www.devmedia.com.br/inversao-de-controle-x-injecao-de-dependencia/18763)
- [Entendendo Coesão e Acoplamento](https://www.devmedia.com.br/entendendo-coesao-e-acoplamento/18538)
