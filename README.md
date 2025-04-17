```mermaid
flowchart TD
    subgraph 사용자
        A1[🧑 사용자]
    end

    subgraph 클라이언트 화면 (Thymeleaf HTML)
        A2[📄 index.html<br>/article/list.html]
        A3[📝 /article/form.html]
    end

    subgraph Controller
        B1[🎯 MainController<br>@GetMapping("/")<br>@GetMapping("/file/{filename}")]
        B2[🎯 ArticleController<br>@GetMapping("/article")]
        B3[🎯 ArticleController<br>@GetMapping("/article/new")]
        B4[🎯 ArticleController<br>@PostMapping("/article/new")]
        B5[🎯 ArticleController<br>@GetMapping("/edit/{uuid}")]
        B6[🎯 ArticleController<br>@PostMapping("/edit/{uuid}")]
        B7[🎯 ArticleController<br>@PostMapping("/delete/{uuid}")]
    end

    subgraph Service
        C1[🛠️ FileServiceImpl<br>upload(), download()]
        C2[📦 ArticleServiceImpl<br>save(), findAll(), findById(), delete()]
    end

    subgraph 저장소
        D1[☁️ Supabase<br>파일 저장소]
        D2[🗂️ ArticleRepository]
        D3[(🧠 DB)]
    end

    subgraph 예외 처리
        E1[❌ BadFileException<br>→ form 재렌더링]
        E2[❌ BadDataException<br>→ form 재렌더링]
        E3[❌ Supabase 다운로드 실패<br>→ 500 응답]
    end

    %% 사용자 → HTML
    A1 -->|GET /article| A2
    A1 -->|GET /article/new| A3
    A1 -->|POST /article/new| B4
    A1 -->|POST /article/edit/{uuid}| B6
    A1 -->|POST /article/delete/{uuid}| B7
    A1 -->|GET /file/{filename}| B1

    %% HTML → Controller
    A2 --> B2
    A3 --> B3

    %% Controller → Service → 저장
    B4 -->|파일 업로드| C1 -->|요청| D1
    C1 --업로드 성공--> B4
    C1 --BadFileException--> E1 --> A3
    B4 -->|파일명 설정 후 저장| C2 -->|유효성 검사| D2 --> D3
    C2 --BadDataException--> E2 --> A3

    %% 수정 흐름
    B6 -->|파일 업로드| C1 -->|요청| D1
    C1 --업로드 성공--> B6
    C1 --BadFileException--> E1 --> A3
    B6 -->|파일명 설정 후 수정 저장| C2 -->|유효성 검사| D2 --> D3
    C2 --BadDataException--> E2 --> A3

    %% 글 목록
    B2 --> C2 --> D2 --> D3

    %% 글 수정 진입
    B5 --> C2 --> D2 --> D3

    %% 글 삭제
    B7 --> C2 --> D2 --> D3

    %% 파일 다운로드
    B1 --> C1 --> D1
    C1 --다운로드 실패--> E3

    style A1 fill:#fdf2e9,stroke:#e67e22
    style A2 fill:#fef9e7,stroke:#f39c12
    style A3 fill:#fef9e7,stroke:#f39c12
    style B1 fill:#e8f8f5,stroke:#1abc9c
    style B2 fill:#e8f8f5,stroke:#1abc9c
    style B3 fill:#e8f8f5,stroke:#1abc9c
    style B4 fill:#e8f8f5,stroke:#1abc9c
    style B5 fill:#e8f8f5,stroke:#1abc9c
    style B6 fill:#e8f8f5,stroke:#1abc9c
    style B7 fill:#e8f8f5,stroke:#1abc9c
    style C1 fill:#fce4ec,stroke:#ec407a
    style C2 fill:#e8f5e9,stroke:#2ecc71
    style D1 fill:#f9fbe7,stroke:#9ccc65
    style D2 fill:#d6eaf8,stroke:#3498db
    style D3 fill:#ede7f6,stroke:#8e44ad
    style E1 fill:#fdecea,stroke:#e74c3c
    style E2 fill:#fdecea,stroke:#e74c3c
    style E3 fill:#fdecea,stroke:#e74c3c
```
