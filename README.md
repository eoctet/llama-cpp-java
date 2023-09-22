# ☕️ Java bindings for [`llama.cpp`](https://github.com/ggerganov/llama.cpp)

[**🇨🇳中文**](./README.Zh_CN.md) | [**🌐English**](./README.md)

Another simple Java bindings for 🦙 [**llama.cpp**](https://github.com/ggerganov/llama.cpp), this project has the same functionality as other language versions.

#### Main content
- 🚀 Built based on Llama.cpp, supports GGUF model. For more details, please follow **@ggerganov's** [`llama.cpp`](https://github.com/ggerganov/llama.cpp)
- 🚀 Supported:
  - [X] OpenAPI (Some sampling parameters are adjusted to Llama2)
  - [X] Multi-user sessions
  - [X] Cloud deployment
  - [X] CLI interaction


## Usages


#### ConsoleQA

```java
public class ConsoleQA {

    private static final String MODEL_PATH = "/llama.cpp/models/llama2/ggml-model-7b-q6_k.gguf";

    public static void main(String[] args) {
        ModelParameter modelParams = ModelParameter.builder()
                .modelPath(MODEL_PATH)
                .threads(8)
                .contextSize(4096)
                .verbose(true)
                .build();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));
             Model model = new Model(modelParams)) {

            GenerateParameter generateParams = GenerateParameter.builder().build();
            String system = "Answer the questions.";

            while (true) {
                System.out.print("\nQuestion: ");
                String input = bufferedReader.readLine();
                if (StringUtils.trimToEmpty(input).equalsIgnoreCase("exit")) {
                    break;
                }
                String question = PromptBuilder.toPrompt(system, input);
                model.generate(generateParams, question).forEach(e -> System.out.print(e.getText()));
                model.printTimings();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e);
            System.exit(1);
        }
    }
}
```

#### Open API

- **`COMPLETIONS`**

```bash
curl --location 'http://SERVER:PORT/v1/completions' \
--header 'Content-Type: application/json' \
--data '{
    "user": "William",
    "stream": true,
    "input": "Who are you",
    "prompt": "<YOUR PROMPTS>"
}'
```

- **`CHAT`**

```bash
curl --location 'http://SERVER:PORT/v1/chat/completions' \
--header 'Content-Type: application/json' \
--data '{
    "user": "William",
    "stream": true,
    "messages": [
        {
            "role": "USER",
            "content": "Who are you"
        }
    ]
}'
```

> [!ATTENTIONS]
>
> This project does not include language model. Please obtain the required model files yourself.
> 
> Some features are being optimized and updated.

## Feedback

- If you have any questions, please submit them in GitHub Issue.

----

[![License: MIT](https://img.shields.io/badge/license-MIT-blue.svg)](https://opensource.org/licenses/MIT)
