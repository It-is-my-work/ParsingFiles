package guru.qa.parsing.tests;

import guru.qa.parsing.utils.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

public class FilesParsingTests {

    JsonParser jsonParser = new JsonParser();
    ZipParser zipParser = new ZipParser();

    static Stream<Arguments> checkFileContent() {
        return Stream.of(
                Arguments.of("xlsTestData.xls",
                        List.of("1", "iivanova@company.ru")),
                Arguments.of("pdfTestData.pdf",
                        List.of("29", "THE ARTSTATION GUIDE")),
                Arguments.of("csvTestData.csv",
                        List.of("1", "OU001;;Коммерческий департамент"))
        );
    }

    @MethodSource("checkFileContent")
    @ParameterizedTest(name = "Checking the contents of the file {0}")
    void fileContentTest(String fileName, List<String>info) throws Exception {
        zipParser.checkingZipContents(fileName, Integer.parseInt(info.get(0)), info.get(1));
    }

    @Test
    @DisplayName("Checking the contents of JSON")
    void jsonTypeTest() throws IOException {
        jsonParser.checkJson("json/jsonTestData.json", "All Prints", 12);
    }
}
