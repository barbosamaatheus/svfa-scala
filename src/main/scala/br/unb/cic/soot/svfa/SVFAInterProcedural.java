package br.unb.cic.soot.svfa;


import br.unb.cic.soot.analisys.AbstractMergeConflictDefinition;

public class SVFAInterProcedural extends SVFAAnalysis {
    public SVFAInterProcedural(String classPath, AbstractMergeConflictDefinition definition) {
        super(classPath, definition);
    }

    @Override
    public boolean interproceduralAnalysis() {
        return true;
    }
}