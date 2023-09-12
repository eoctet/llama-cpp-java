package chat.octet.test;

import chat.octet.model.LlamaModel;
import chat.octet.model.parameters.GenerateParameter;
import chat.octet.model.parameters.ModelParameter;
import chat.octet.utils.PromptBuilder;

public class SimpleExample {
    private static final String MODEL_PATH = "/llama.cpp/models/llama2/ggml-model-7b-q6_k.gguf";

    public static void main(String[] args) {
        ModelParameter modelParams = ModelParameter.builder()
                .modelPath(MODEL_PATH)
                .threads(8)
                .contextSize(4096)
                .verbose(true)
                .build();

        String text = PromptBuilder.toPrompt(
                "Answer the questions.",
                "Who are you?"
        );

        GenerateParameter generateParams = GenerateParameter.builder().build();

        try (LlamaModel model = new LlamaModel(modelParams)) {
            model.generate(generateParams, text).forEach(e -> System.out.print(e.getText()));
        }
    }
}
