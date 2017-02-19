package fm.pattern.validation.sequences;

import javax.validation.GroupSequence;

@GroupSequence({ UpdateLevel1.class, UpdateLevel2.class, UpdateLevel3.class, UpdateLevel4.class, UpdateLevel5.class })
public interface Update {

}
