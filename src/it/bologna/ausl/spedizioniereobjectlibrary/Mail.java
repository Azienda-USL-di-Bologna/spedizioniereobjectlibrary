package it.bologna.ausl.spedizioniereobjectlibrary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
    @JsonSubTypes.Type(value = Mail.class, name = "Mail"),
})
public class Mail {
    
    private String from;
    private String to;
    private String cc;
    private String ccn;
    private String subject;
    private String message;
    private List<Attachment> attachments;

    public Mail() {
    }

    public Mail(String from, String to, String cc, String ccn, String subject, String message) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.ccn = ccn;
        this.subject = subject;
        this.message = message;
    }
    
    public Mail(String from, String to, String cc, String ccn, String subject, String message, List<Attachment> attachments) {
        this.from = from;
        this.to = to;
        this.cc = cc;
        this.ccn = ccn;
        this.subject = subject;
        this.message = message;
        this.attachments = attachments;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }
    
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCcn() {
        return ccn;
    }

    public void setCcn(String ccn) {
        this.ccn = ccn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }
    
    @JsonIgnore
    public void addAttachment(Attachment attachment) {
        if (attachments == null)
            attachments = new ArrayList<>();
        attachments.add(attachment);
    }
    
    @JsonIgnore
    public Attachment getAttachment(int index){
        return attachments.get(index);
    }

    @JsonIgnore
    public int getAttachmentsSize(){
        if(attachments == null)
            return 0;
        else
            return attachments.size();
    }
    
    @JsonIgnore
    public String getJSONString() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        String writeValueAsString = mapper.writeValueAsString(this);
        return writeValueAsString;
    }
    
    @JsonIgnore
    public static Mail parse(String value) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setTimeZone(TimeZone.getDefault());
        return mapper.readValue(value, Mail.class);
    }
    
}
