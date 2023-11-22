# 🤖️ Llama Java Chat

[![CI](https://github.com/eoctet/llama-java-chat/actions/workflows/maven_build_deploy.yml/badge.svg)](https://github.com/eoctet/llama-java-chat/actions/workflows/maven_build_deploy.yml)
[![README Zh_CN](https://img.shields.io/badge/Lang-中文-red)](./README.Zh_CN.md)
[![Llama java core](https://img.shields.io/badge/Github-Llama_Java_Core-blue?logo=github)](https://github.com/eoctet/llama-java-core.git)
[![GitHub](https://img.shields.io/github/license/eoctet/llama-java-core)](https://opensource.org/licenses/MIT)


This is a 🦙 `Llama chat robot service`. You can use it to deploy your own private services, support `Llama2` series models and other open source models.

#### Features

- [X] 🚀 `OpenAPI` (Some inference parameters are adjusted to Llama2)
- [X] 🚀 Continuous generation and chat
- [X] 🚀 Web UI, Like [`ChatGPT Next Web`](https://github.com/Yidadaa/ChatGPT-Next-Web)
- [X] 🚀 Cloud deployment
- [X] 🚀 CLI interaction


## Quick start


#### 🖥 Cloud deployment

- Download & Starting server

```bash
# Default URL: http://YOUR_IP_ADDR:8152/

cd <YOUR_PATH>/chat-server & bash app_server.sh start
```

- Directory

```text
=> chat-server
   ⌊___ chat-server.jar
   ⌊___ app_server.sh
   ⌊___ conf
        ⌊___ setting.json

···
```

Following the interface specifications of `ChatGPT`, only the main interfaces are implemented, It can be integrated with [`ChatGPT Next Web`](https://github.com/Yidadaa/ChatGPT-Next-Web), WebUI, and App for use.

> ℹ️ __Differences__
> 1. Added parameters for the Llama series model and removed unsupported GPT parameters;
> 2. By default, the `Llama2-chat` prompt template is used. If you need to adapt to other models, you can adjust it yourself;
> 3. There are no unnecessary functions such as requesting authentication and usage queries;
> 4. Optimize the conversation and chat API, without the need to pass on historical conversation context, only the current conversation content is sufficient.
>
> > More information: [`API Docs`](docs/API.md).

![webui.png](docs%2Fwebui.png)

For example

> `POST` **/v1/chat/completions**

```shell
curl --location 'http://127.0.0.1:8152/v1/chat/completions' \
--header 'Content-Type: application/json' \
--data '{
    "messages": [
        {
            "role": "SYSTEM",
            "content": "<YOUR_PROMPT>"
        },
        {
            "role": "USER",
            "content": "Who are you?"
        }
    ],
    "user": "william",
    "verbose": true,
    "stream": true,
    "model": "Llama2-chat"
}'
```

The API will return data in a stream format:

```json
{
    "id": "octetchat-98fhd2dvj7",
    "model": "Llama2-chat",
    "created": 1695614393810,
    "choices": [
        {
            "index": 0,
            "delta": {
                "content": "Hi"
            },
            "finish_reason": "NONE"
        }
    ]
}
```

#### 🤖 CLI interaction

Run command line interaction and specify the language model that needs to be loaded.

```bash
java -jar chat-console.jar --model llama2-chat --system 'YOUR_PROMPT'
```

```txt
... ...

User: Who are you
AI: As an AI, I don't know who I am. My designers and creators created me. 
However, I am a virtual assistant designed to provide assistance and answer questions.
```

> Use `help` to view more generate parameters, for example:

```bash
java -jar chat-console.jar --help

usage: LLAMA-JAVA-CHAT v1.1.4
 -c,--completions               Use completions mode.
    --frequency-penalty <arg>   Repeat alpha frequency penalty (default:
                                0.0, 0.0 = disabled)
 -h,--help                      Show this help message and exit.
    --keep <arg>                Number of tokens to keep from the context.
 -m,--model <arg>               Load model name, default: llama2-chat.
    --max-new-tokens <arg>      Maximum new token generation size
                                (default: 0 unlimited).
    --mirostat <arg>            Enable Mirostat sampling, controlling
                                perplexity during text generation
                                (default: 0, 0 = disabled, 1 = Mirostat, 2
                                = Mirostat 2.0).
    --mirostat-ent <arg>        Set the Mirostat target entropy, parameter
                                tau (default: 5.0).
    --mirostat-lr <arg>         Set the Mirostat learning rate, parameter
                                eta (default: 0.1).
    --no-penalize-nl <arg>      Disable penalization for newline tokens
                                when applying the repeat penalty (default:
                                true).
    --presence-penalty <arg>    Repeat alpha presence penalty (default:
                                0.0, 0.0 = disabled)
    --repeat-penalty <arg>      Control the repetition of token sequences
                                in the generated text (default: 1.1).
    --system <arg>              Set a system prompt.
    --temperature <arg>         Adjust the randomness of the generated
                                text (default: 0.8).
    --tfs <arg>                 Enable tail free sampling with parameter z
                                (default: 1.0, 1.0 = disabled).
    --top-k <arg>               Top-k sampling (default: 40, 0 =
                                disabled).
    --top-p <arg>               Top-p sampling (default: 0.9).
    --min-p <arg>               Min-p sampling (default: 0.05, 0 = disabled).
    --typical <arg>             Enable typical sampling sampling with
                                parameter p (default: 1.0, 1.0 =
                                disabled).
    --verbose-prompt            Print the prompt before generating text.
```

#### ⚙️ Build (optional)

Build with `Maven`:

```bash
git clone https://github.com/eoctet/llama-java-chat.git

# Maven build
cd llama-java-chat

# Build app type: server / console
bash maven_build.sh server
```


> ⚠️ __ATTENTIONS__
>
> This project does not include language model. Please obtain the required model files yourself.

## Feedback

- If you have any questions, please submit them in GitHub Issue.

