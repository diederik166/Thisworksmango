package br.org.scadabr.vo.dataSource.opc;

public class OPCDataSourceVOData {
	public int updatePeriodType;
	@JsonRemoteProperty
	public int updatePeriods;
	@JsonRemoteProperty
	public String host;
	@JsonRemoteProperty
	public String domain;
	@JsonRemoteProperty
	public String user;
	@JsonRemoteProperty
	public String password;
	@JsonRemoteProperty
	public String server;

	public OPCDataSourceVOData(int updatePeriodType, int updatePeriods, String host, String domain, String user,
			String password, String server) {
		this.updatePeriodType = updatePeriodType;
		this.updatePeriods = updatePeriods;
		this.host = host;
		this.domain = domain;
		this.user = user;
		this.password = password;
		this.server = server;
	}
}