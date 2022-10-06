#zipkin 설정방법

우선 dependency를 추가해야함.

gradle 기준 
~~~
implementation 'org.springframework.cloud:spring-cloud-starter-sleuth'
implementation 'org.springframework.cloud:spring-cloud-starter-zipkin:2.2.5.RELEASE'
implementation 'org.springframework.boot:spring-boot-starter-actuator'
~~~
maven인 pom.xml은 https://mvnrepository.com/ 검색후 넣어야함
높은 버전은 잘안되는거 같으니, 해보고 안되면 낮은버전 넣어보는걸 추천합니다.(아래 버전은 임의로 넣음)
~~~
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-sleuth</artifactId>
    <version>3.1.1</version>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-zipkin</artifactId>
    <version>2.2.8.RELEASE</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
    <version>2.6.6</version>
</dependency>
~~~

sleuth를 추가하게되면 로그에 id가 생기게되어, 분산환경에서 trace 할수있게됩니당.

그후 yaml에 아래 내용을 추가하면, zipkin 서버에 로그를 보낼수 있음. 
(내부적으로 로그 하나가 생기면 RestTemplate으로 zipkin서버에 보내는 구조)

~~~
spring:
  zipkin:
    base-url: http://amdp-zipkin.hrd-edu.cloudzcp.com
~~~


##활동이력, 판매로 전송하는 예약 layout

~~~
public class Reservation extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long sellerId;
    private Long buyerId;
    private Long chatRoomId; // 채팅방 아이디
    private String time; // 예약시간
    private String location; // 예약장소
    @Enumerated(EnumType.STRING)
    private Status status; // 상태 (예약, 예약취소)

    public enum Status {
        RESERVED, CANCLED
    }
}
~~~

# 채팅 흐름
### 처음 시작할때
클) 채팅방 생성 -> 


