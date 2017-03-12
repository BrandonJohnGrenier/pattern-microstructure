package fm.pattern.valex.sequences;

import javax.validation.GroupSequence;

@GroupSequence({ DeleteLevel1.class, DeleteLevel2.class, DeleteLevel3.class, DeleteLevel4.class, DeleteLevel5.class })
public interface Delete {

}
