package com.example.chatapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "CHAT APP API",
        description = """
            **채팅 어플 Swagger에 오신 걸 환영합니다!** \s
            코드로 만나서 반갑습니다 😊

            ## 공지사항
            - 이 API 문서는 Swagger UI에서 제공되며, 아래 가이드를 따라 로컬 서버와 테스트 서버에서 API를 테스트할 수 있습니다.

            ## 서버 선택 방법
            현재 제공되는 서버 목록은 다음과 같습니다:
            1. **로컬 서버:** `http://localhost:8080`
            2. **테스트 서버:** `https://api.test.example.com`
            3. **메인 서버:** `https://api.example.com`

            기본적으로 **로컬 서버**에서 테스트하는 것을 권장합니다. \s
            서버 선택은 **서버 목록**에서 로컬 서버를 클릭하여 설정할 수 있습니다.

            ## JWT 토큰 등록 및 사용 방법
            1. **JWT 토큰 발급:** 인증 API를 호출하여 JWT 토큰을 발급받습니다. (토큰은 'Authorization' 헤더에 추가되어야 합니다.)
            2. **토큰 등록:** Swagger UI의 우측 상단에 있는 **Authorize** 버튼을 클릭합니다.
            3. **Bearer Token 입력:** 팝업창에서 토큰 입력 필드에 `Bearer <your_token>` 형식으로 발급받은 JWT 토큰을 입력합니다.
               - `Bearer `와 토큰 사이에 공백을 반드시 포함해야 합니다.
            4. **적용 (Authorize):** 등록 후 **Authorize** 버튼을 눌러 토큰을 적용하면, 인증이 필요한 API 요청 시 자동으로 헤더에 JWT 토큰이 포함됩니다.

            토큰이 만료된 경우 새로 발급받아 위 절차에 따라 다시 등록해 주세요. \s
            모든 API 호출에 동일한 JWT 토큰이 사용되므로, 필요한 권한에 따라 토큰을 발급받으시기 바랍니다.

            > **Note:** 테스트 중 문제가 발생하면, 토큰이 올바르게 등록되었는지 확인해 보세요.

            ## Git 커밋 컨벤션 및 협업 가이드
                        
            ### 1. 브랜치 사용 가이드
            - **본인 브랜치에서 작업하기:** \s
              각 개발자는 자신의 브랜치에서 작업을 진행해야 합니다. 
              예를 들어, 제 브랜치는 `suh`입니다. 
              본인의 브랜치를 생성하고, 해당 브랜치에서 기능 개발이나 버그 수정을 진행합니다.

            
            ### 2. 커밋 메시지 컨벤션
            모든 커밋 메시지는 일관된 형식을 유지해야 하며, 다음과 같은 접두사를 사용합니다:
                        
            - **fix:** 버그 수정 \s
              예) `fix: 회원 가져오기 인증 로직 수정`
                        
            - **feat:** 새로운 기능 추가 \s
              예) `feat: 회원 로그인 API 구현`
                        
            - **chore:** 빌드 관련 설정, 패키지 매니저 설정 등 \s
              예) `chore: Gradle 의존성 업데이트`
                        
            - **init:** 프로젝트 초기 설정 \s
              예) `init: 프로젝트 초기 설정 및 기본 구조 생성`
                        
            - **refactor:** 코드 리팩토링 \s
              예) `refactor: 회원 서비스 로직 개선`
                        
            - **docs:** 문서 관련 변경 \s
              예) `docs: README.md 업데이트`
                        
            **주의사항:**
            - 커밋 메시지는 명확하고 간결하게 작성하여 작업 내용을 쉽게 파악할 수 있도록 합니다.
            - 현재 작업한 내용이 무엇인지 쉽게 파악할 수 있도록 작성합니다.
            
            ### 3. PR 생성하기
            PR올리기전에 이거 github desktop 에서 위에 branch > update from main 이거 누르면 main 코드 받아옵니다 ( 그전에 안받아와지면 fetch origin 한번 누르고 시작)
            ( conflict가 일어나면 해결하세요 )
            
            본인 코드 커밋 이후 -> github desktop 오른쪽위에  Push Origin 클릭 -> 그 후에 PR 생성
                        
            코드 리뷰:
            동료 개발자에게 PR을 리뷰해 달라고 요청합니다. 코드 리뷰어는 코드의 품질, 기능 구현 여부, 버그 등을 검토합니다.
                        
            피드백 반영:
            리뷰어의 피드백을 반영하여 필요한 수정 작업을 진행합니다.
                        
            PR 승인 및 병합:
            모든 리뷰어의 승인을 받은 후, PR을 main 브랜치에 병합합니다.

            ### 4. GitHub Desktop 사용 권장
            GitHub Desktop은 Git을 시각적으로 관리할 수 있는 도구로, Git 명령어에 익숙하지 않은 사용자도 쉽게 사용할 수 있습니다. **GitHub Desktop**을 사용하면 다음과 같은 장점이 있습니다:
            - **쉬운 브랜치 관리:** 브랜치 생성, 전환, 병합 등을 직관적으로 수행할 수 있습니다.
            - **시각적 커밋 히스토리:** 커밋 로그를 그래픽으로 확인할 수 있어 변경사항을 쉽게 추적할 수 있습니다.
            - **Conflict 해결:** 병합 충돌 시 시각적 도구를 통해 쉽게 해결할 수 있습니다.
                        
            **GitHub Desktop 다운로드:** \s
            [GitHub Desktop 공식 사이트](https://desktop.github.com/)에서 다운로드하여 설치할 수 있습니다.
                        """,
        version = "1.0v"
    ),
    servers = {
//        @Server(url = "https://api.example.com", description = "메인 서버"),
//        @Server(url = "https://api.test.example.com", description = "테스트 서버"),
        @Server(url = "http://localhost:8080", description = "로컬 서버")
    }
)
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    SecurityScheme apiKey = new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .in(SecurityScheme.In.HEADER)
        .name("Authorization")
        .scheme("bearer")
        .bearerFormat("JWT");

    SecurityRequirement securityRequirement = new SecurityRequirement()
        .addList("Bearer Token");

    return new OpenAPI()
        .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
        .addSecurityItem(securityRequirement)
        .servers(List.of(
                new io.swagger.v3.oas.models.servers.Server()
                    .url("http://localhost:8080")
                    .description("로컬 서버")
//                new io.swagger.v3.oas.models.servers.Server()
//                    .url("https://api.test.example.com")
//                    .description("테스트 서버"),
//                new io.swagger.v3.oas.models.servers.Server()
//                    .url("https://api.example.com")
//                    .description("메인 서버")
            )
        );
  }
}
