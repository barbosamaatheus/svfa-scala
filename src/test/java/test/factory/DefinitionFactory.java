package test.factory;


import br.unb.cic.soot.analisys.AbstractMergeConflictDefinition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DefinitionFactory {

    public static AbstractMergeConflictDefinition definition(String className, int sourceLines[], int sinkLines[]) {
        return new AbstractMergeConflictDefinition() {
            @Override
            protected Map<String, List<Integer>> sourceDefinitions() {
                Map<String, List<Integer>> res = new HashMap<>();
                List<Integer> lines = Arrays.stream(sourceLines).boxed().collect(Collectors.toList());
                res.put(className, lines);
                return res;
            }

            @Override
            protected Map<String, List<Integer>> sinkDefinitions() {
                Map<String, List<Integer>> res = new HashMap<>();
                List<Integer> lines = Arrays.stream(sinkLines).boxed().collect(Collectors.toList());
                res.put(className, lines);
                return res;
            }
        };
    }
}
