package wholemusic.core.provider.migu;

import com.alibaba.fastjson.annotation.JSONField;
import wholemusic.core.model.Artist;
import wholemusic.core.model.BaseBean;

@SuppressWarnings("SpellCheckingInspection")
class MiguArtist extends BaseBean implements Artist {
    @JSONField(name = "name")
    public String name;

    @JSONField(name = "id")
    public String id;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getId() {
        return id;
    }
}
