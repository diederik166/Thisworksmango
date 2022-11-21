package br.org.scadabr.vo.dataSource.opc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import br.org.scadabr.rt.dataSource.opc.OPCDataSourceRT;

import com.serotonin.json.JsonException;
import com.serotonin.json.JsonObject;
import com.serotonin.json.JsonReader;
import com.serotonin.json.JsonRemoteEntity;
import com.serotonin.mango.Common;
import com.serotonin.mango.rt.dataSource.DataSourceRT;
import com.serotonin.mango.rt.event.type.AuditEventType;
import com.serotonin.mango.util.ExportCodes;
import com.serotonin.mango.vo.dataSource.DataSourceVO;
import com.serotonin.mango.vo.dataSource.PointLocatorVO;
import com.serotonin.mango.vo.event.EventTypeVO;
import com.serotonin.util.StringUtils;
import com.serotonin.web.dwr.DwrResponseI18n;
import com.serotonin.web.i18n.LocalizableMessage;

@JsonRemoteEntity
public class OPCDataSourceVO extends DataSourceVO<OPCDataSourceVO> {
    public static final Type TYPE = Type.OPC;

    @Override
    protected void addEventTypes(List<EventTypeVO> eventTypes) {
        eventTypes.add(createEventType(OPCDataSourceRT.POINT_READ_EXCEPTION_EVENT, new LocalizableMessage(
                "event.ds.pointRead")));
        eventTypes.add(createEventType(OPCDataSourceRT.DATA_SOURCE_EXCEPTION_EVENT, new LocalizableMessage(
                "event.ds.dataSource")));
        eventTypes.add(createEventType(OPCDataSourceRT.POINT_WRITE_EXCEPTION_EVENT, new LocalizableMessage(
                "event.ds.dataSource")));
    }

    private static final ExportCodes EVENT_CODES = new ExportCodes();
    static {
        EVENT_CODES.addElement(OPCDataSourceRT.DATA_SOURCE_EXCEPTION_EVENT, "DATA_SOURCE_EXCEPTION");
        EVENT_CODES.addElement(OPCDataSourceRT.POINT_READ_EXCEPTION_EVENT, "POINT_READ_EXCEPTION");
        EVENT_CODES.addElement(OPCDataSourceRT.POINT_WRITE_EXCEPTION_EVENT, "POINT_WRITE_EXCEPTION");
    }

    @Override
    public DataSourceRT createDataSourceRT() {
        return new OPCDataSourceRT(this);
    }

    @Override
    public PointLocatorVO createPointLocator() {
        return new OPCPointLocatorVO();
    }

    @Override
    public LocalizableMessage getConnectionDescription() {
        return new LocalizableMessage("common.noMessage");
    }

    @Override
    public ExportCodes getEventCodes() {
        return EVENT_CODES;
    }

    @Override
    public DataSourceVO.Type getType() {
        return TYPE;
    }

    public OPCDataSourceVOData data = new OPCDataSourceVOData(Common.TimePeriods.SECONDS, 1, "localhost", "localhost", "", "", "");

	@Override
    public void validate(DwrResponseI18n response) {
        super.validate(response);
        if (StringUtils.isEmpty(data.host))
            response.addContextualMessage("host", "validate.required");
        // if (StringUtils.isEmpty(domain))
        // response.addContextualMessage("domain", "validate.required");
        if (StringUtils.isEmpty(data.user))
            response.addContextualMessage("user", "validate.required");
        if (StringUtils.isEmpty(data.password))
            response.addContextualMessage("password", "validate.required");
        if (StringUtils.isEmpty(data.server))
            response.addContextualMessage("server", "validate.required");
        if (data.updatePeriods <= 0)
            response.addContextualMessage("updatePeriods", "validate.greaterThanZero");
    }

    @Override
    protected void addPropertiesImpl(List<LocalizableMessage> list) {
        AuditEventType.addPeriodMessage(list, "dsEdit.updatePeriod", data.updatePeriodType, data.updatePeriods);
        AuditEventType.addPropertyMessage(list, "dsEdit.opc.host", data.host);
        AuditEventType.addPropertyMessage(list, "dsEdit.opc.domain", data.domain);
        AuditEventType.addPropertyMessage(list, "dsEdit.opc.user", data.user);
        AuditEventType.addPropertyMessage(list, "dsEdit.opc.password", data.password);
        AuditEventType.addPropertyMessage(list, "dsEdit.opc.server", data.server);
    }

    @Override
    protected void addPropertyChangesImpl(List<LocalizableMessage> list, OPCDataSourceVO from) {
        AuditEventType.maybeAddPeriodChangeMessage(list, "dsEdit.updatePeriod", from.data.updatePeriodType,
                from.data.updatePeriods, data.updatePeriodType, data.updatePeriods);
        AuditEventType.maybeAddPropertyChangeMessage(list, "dsEdit.opc.host", from.data.host, data.host);
        AuditEventType.maybeAddPropertyChangeMessage(list, "dsEdit.opc.domain", from.data.domain, data.domain);
        AuditEventType.maybeAddPropertyChangeMessage(list, "dsEdit.opc.user", from.data.user, data.user);
        AuditEventType.maybeAddPropertyChangeMessage(list, "dsEdit.opc.password", from.data.password, data.password);
        AuditEventType.maybeAddPropertyChangeMessage(list, "dsEdit.opc.server", from.data.server, data.server);
    }

    public int getUpdatePeriodType() {
        return data.updatePeriodType;
    }

    public void setUpdatePeriodType(int updatePeriodType) {
        this.data.updatePeriodType = updatePeriodType;
    }

    public int getUpdatePeriods() {
        return data.updatePeriods;
    }

    public void setUpdatePeriods(int updatePeriods) {
        this.data.updatePeriods = updatePeriods;
    }

    public String getHost() {
        return data.host;
    }

    public void setHost(String host) {
        this.data.host = host;
    }

    public String getDomain() {
        return data.domain;
    }

    public void setDomain(String domain) {
        this.data.domain = domain;
    }

    public String getUser() {
        return data.user;
    }

    public void setUser(String user) {
        this.data.user = user;
    }

    public String getPassword() {
        return data.password;
    }

    public void setPassword(String password) {
        this.data.password = password;
    }

    public String getServer() {
        return data.server;
    }

    public void setServer(String server) {
        this.data.server = server;
    }

    //
    // /
    // / Serialization
    // /
    //
    private static final long serialVersionUID = -1;
    public static final int version = 1;

    /**
	 * @deprecated Use {@link com.serotonin.mango.util.ExportCodes#writeObject(br.org.scadabr.vo.dataSource.opc.OPCDataSourceVO,ObjectOutputStream)} instead
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {
		EVENT_CODES.writeObject(this, out);
	}

    /**
	 * @deprecated Use {@link com.serotonin.mango.util.ExportCodes#readObject(br.org.scadabr.vo.dataSource.opc.OPCDataSourceVO,ObjectInputStream)} instead
	 */
	private void readObject(ObjectInputStream in) throws IOException {
		EVENT_CODES.readObject(this, in);
	}

    @Override
    public void jsonDeserialize(JsonReader reader, JsonObject json) throws JsonException {
        super.jsonDeserialize(reader, json);
        Integer value = deserializeUpdatePeriodType(json);
        if (value != null)
            data.updatePeriodType = value;
    }

    @Override
    public void jsonSerialize(Map<String, Object> map) {
        super.jsonSerialize(map);
        serializeUpdatePeriodType(map, data.updatePeriodType);
    }
}
