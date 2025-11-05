package it.bologna.ausl.spedizioniereobjectlibrary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.TimeZone;

/**
 *
 * @author andrea
 */
@JsonTypeInfo(  
    use = JsonTypeInfo.Id.NAME,  
    include = JsonTypeInfo.As.PROPERTY,  
    property = "__CLASS_TYPE__")  
@JsonSubTypes({  
    @JsonSubTypes.Type(value = Attachment.class, name = "Attachment"),
})
public class Attachment {
    
    private String name;
    private String uuid;
    private String mimetype;
    private String link;
    private boolean protetto;

    public Attachment() {
    }

    public Attachment(String name, String uuid, String mimetype, Long bytesSize, String link ,boolean protetto) {
        this.name = name;
        this.uuid = uuid;
        this.mimetype = mimetype;
        this.link = link;
        this.protetto = protetto;
    }
    
    public Attachment(String name, String uuid, String mimetype) {
        this.name = name;
        this.uuid = uuid;
        this.mimetype = mimetype;
    }

    public String getName() {
        return name;
    }

    public void setNome(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isProtetto() {
        return protetto;
    }

    public void setProtetto(boolean protetto) {
        this.protetto = protetto;
    }
    
    @JsonIgnore
    public String getJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        String writeValueAsString = mapper.writeValueAsString(this);
        return writeValueAsString;
    }
}
