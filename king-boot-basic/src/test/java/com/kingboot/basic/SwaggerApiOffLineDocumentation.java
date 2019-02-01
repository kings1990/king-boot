package com.kingboot.basic;


import io.github.swagger2markup.GroupBy;
import io.github.swagger2markup.Swagger2MarkupConverter;
import io.github.swagger2markup.builder.Swagger2MarkupConfigBuilder;
import io.github.swagger2markup.markup.builder.MarkupLanguage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;
import java.nio.file.Paths;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

@AutoConfigureMockMvc
@AutoConfigureRestDocs (outputDir = "target/generated-snippets")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SwaggerApiOffLineDocumentation {

    private String snippetDir = "target/generated-snippets";
    // 这个outputDir必须和插件里面<generated></generated>标签配置一致
    private String outputDir = "target/asciidoc/generated";
    
    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    public void testKings() {
        System.err.println("========离线文档adoc生成中......========");
    }
    
    @After
    public void Test() throws Exception{
        MockHttpServletRequestBuilder accept = get("https://ws.com/boot/swagger/api").accept(MediaType.APPLICATION_JSON);
        // 得到swagger.json,写入outputDir目录中
        // mockMvc.perform(accept)
        //         .andDo(SwaggerResultHandler.outputDirectory(outputDir).build())
        //         .andExpect(status().isOk())
        //         .andReturn();
        Swagger2MarkupConverter.from(new URL("https://ws.com/boot/swagger/api"))
                .withConfig(new Swagger2MarkupConfigBuilder()
                        .withMarkupLanguage(MarkupLanguage.ASCIIDOC)
                        .withPathsGroupedBy(GroupBy.TAGS)
                        .build()
                ).build().toFolder(Paths.get(outputDir));
    }
    
    

    
}