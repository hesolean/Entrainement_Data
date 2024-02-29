// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package etude_de_cas.etude_cas_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Etude_cas Purpose: Préparation de la table<br>
 * Description: Préparation des données pour une utilisation dans Qlick <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Etude_cas implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Etude_cas";
	private final String projectName = "ETUDE_DE_CAS";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Etude_cas.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Etude_cas.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tLogRow_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_2_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_3_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_3_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_7_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_4_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputDelimited_11_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_11_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row4_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row5_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row6_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row8_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row9_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row10_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_6_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tAdvancedHash_row12_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputDelimited_10_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputDelimited_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_6_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void tFileInputDelimited_10_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class ClientStruct implements routines.system.IPersistableRow<ClientStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDClt;

		public String getIDClt() {
			return this.IDClt;
		}

		public String nomClt;

		public String getNomClt() {
			return this.nomClt;
		}

		public String prenomClt;

		public String getPrenomClt() {
			return this.prenomClt;
		}

		public String adresseClt;

		public String getAdresseClt() {
			return this.adresseClt;
		}

		public String villeClt;

		public String getVilleClt() {
			return this.villeClt;
		}

		public String etatClt;

		public String getEtatClt() {
			return this.etatClt;
		}

		public String _V11;

		public String get_V11() {
			return this._V11;
		}

		public String depClt;

		public String getDepClt() {
			return this.depClt;
		}

		public String regionClt;

		public String getRegionClt() {
			return this.regionClt;
		}

		public String paysClt;

		public String getPaysClt() {
			return this.paysClt;
		}

		public String telClt;

		public String getTelClt() {
			return this.telClt;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDClt = readString(dis);

					this.nomClt = readString(dis);

					this.prenomClt = readString(dis);

					this.adresseClt = readString(dis);

					this.villeClt = readString(dis);

					this.etatClt = readString(dis);

					this._V11 = readString(dis);

					this.depClt = readString(dis);

					this.regionClt = readString(dis);

					this.paysClt = readString(dis);

					this.telClt = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDClt = readString(dis);

					this.nomClt = readString(dis);

					this.prenomClt = readString(dis);

					this.adresseClt = readString(dis);

					this.villeClt = readString(dis);

					this.etatClt = readString(dis);

					this._V11 = readString(dis);

					this.depClt = readString(dis);

					this.regionClt = readString(dis);

					this.paysClt = readString(dis);

					this.telClt = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDClt, dos);

				// String

				writeString(this.nomClt, dos);

				// String

				writeString(this.prenomClt, dos);

				// String

				writeString(this.adresseClt, dos);

				// String

				writeString(this.villeClt, dos);

				// String

				writeString(this.etatClt, dos);

				// String

				writeString(this._V11, dos);

				// String

				writeString(this.depClt, dos);

				// String

				writeString(this.regionClt, dos);

				// String

				writeString(this.paysClt, dos);

				// String

				writeString(this.telClt, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDClt, dos);

				// String

				writeString(this.nomClt, dos);

				// String

				writeString(this.prenomClt, dos);

				// String

				writeString(this.adresseClt, dos);

				// String

				writeString(this.villeClt, dos);

				// String

				writeString(this.etatClt, dos);

				// String

				writeString(this._V11, dos);

				// String

				writeString(this.depClt, dos);

				// String

				writeString(this.regionClt, dos);

				// String

				writeString(this.paysClt, dos);

				// String

				writeString(this.telClt, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDClt=" + IDClt);
			sb.append(",nomClt=" + nomClt);
			sb.append(",prenomClt=" + prenomClt);
			sb.append(",adresseClt=" + adresseClt);
			sb.append(",villeClt=" + villeClt);
			sb.append(",etatClt=" + etatClt);
			sb.append(",_V11=" + _V11);
			sb.append(",depClt=" + depClt);
			sb.append(",regionClt=" + regionClt);
			sb.append(",paysClt=" + paysClt);
			sb.append(",telClt=" + telClt);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(ClientStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class VendeurStruct implements routines.system.IPersistableRow<VendeurStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDVend;

		public String getIDVend() {
			return this.IDVend;
		}

		public String nomVend;

		public String getNomVend() {
			return this.nomVend;
		}

		public String prenomVend;

		public String getPrenomVend() {
			return this.prenomVend;
		}

		public String anneeEmbaucheVend;

		public String getAnneeEmbaucheVend() {
			return this.anneeEmbaucheVend;
		}

		public String bureauVend;

		public String getBureauVend() {
			return this.bureauVend;
		}

		public String codeBudgetVend;

		public String getCodeBudgetVend() {
			return this.codeBudgetVend;
		}

		public String dateEmbaucheVend;

		public String getDateEmbaucheVend() {
			return this.dateEmbaucheVend;
		}

		public String nomCmpltVend;

		public String getNomCmpltVend() {
			return this.nomCmpltVend;
		}

		public String fonctionVend;

		public String getFonctionVend() {
			return this.fonctionVend;
		}

		public String gradeVend;

		public String getGradeVend() {
			return this.gradeVend;
		}

		public String niveauVend;

		public String getNiveauVend() {
			return this.niveauVend;
		}

		public String salAnnuelVend;

		public String getSalAnnuelVend() {
			return this.salAnnuelVend;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDVend = readString(dis);

					this.nomVend = readString(dis);

					this.prenomVend = readString(dis);

					this.anneeEmbaucheVend = readString(dis);

					this.bureauVend = readString(dis);

					this.codeBudgetVend = readString(dis);

					this.dateEmbaucheVend = readString(dis);

					this.nomCmpltVend = readString(dis);

					this.fonctionVend = readString(dis);

					this.gradeVend = readString(dis);

					this.niveauVend = readString(dis);

					this.salAnnuelVend = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDVend = readString(dis);

					this.nomVend = readString(dis);

					this.prenomVend = readString(dis);

					this.anneeEmbaucheVend = readString(dis);

					this.bureauVend = readString(dis);

					this.codeBudgetVend = readString(dis);

					this.dateEmbaucheVend = readString(dis);

					this.nomCmpltVend = readString(dis);

					this.fonctionVend = readString(dis);

					this.gradeVend = readString(dis);

					this.niveauVend = readString(dis);

					this.salAnnuelVend = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDVend, dos);

				// String

				writeString(this.nomVend, dos);

				// String

				writeString(this.prenomVend, dos);

				// String

				writeString(this.anneeEmbaucheVend, dos);

				// String

				writeString(this.bureauVend, dos);

				// String

				writeString(this.codeBudgetVend, dos);

				// String

				writeString(this.dateEmbaucheVend, dos);

				// String

				writeString(this.nomCmpltVend, dos);

				// String

				writeString(this.fonctionVend, dos);

				// String

				writeString(this.gradeVend, dos);

				// String

				writeString(this.niveauVend, dos);

				// String

				writeString(this.salAnnuelVend, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDVend, dos);

				// String

				writeString(this.nomVend, dos);

				// String

				writeString(this.prenomVend, dos);

				// String

				writeString(this.anneeEmbaucheVend, dos);

				// String

				writeString(this.bureauVend, dos);

				// String

				writeString(this.codeBudgetVend, dos);

				// String

				writeString(this.dateEmbaucheVend, dos);

				// String

				writeString(this.nomCmpltVend, dos);

				// String

				writeString(this.fonctionVend, dos);

				// String

				writeString(this.gradeVend, dos);

				// String

				writeString(this.niveauVend, dos);

				// String

				writeString(this.salAnnuelVend, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDVend=" + IDVend);
			sb.append(",nomVend=" + nomVend);
			sb.append(",prenomVend=" + prenomVend);
			sb.append(",anneeEmbaucheVend=" + anneeEmbaucheVend);
			sb.append(",bureauVend=" + bureauVend);
			sb.append(",codeBudgetVend=" + codeBudgetVend);
			sb.append(",dateEmbaucheVend=" + dateEmbaucheVend);
			sb.append(",nomCmpltVend=" + nomCmpltVend);
			sb.append(",fonctionVend=" + fonctionVend);
			sb.append(",gradeVend=" + gradeVend);
			sb.append(",niveauVend=" + niveauVend);
			sb.append(",salAnnuelVend=" + salAnnuelVend);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(VendeurStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class CommandeStruct implements routines.system.IPersistableRow<CommandeStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDCmd;

		public String getIDCmd() {
			return this.IDCmd;
		}

		public String IDClt;

		public String getIDClt() {
			return this.IDClt;
		}

		public String IDVend;

		public String getIDVend() {
			return this.IDVend;
		}

		public String dateCmd;

		public String getDateCmd() {
			return this.dateCmd;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readString(dis);

					this.IDClt = readString(dis);

					this.IDVend = readString(dis);

					this.dateCmd = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readString(dis);

					this.IDClt = readString(dis);

					this.IDVend = readString(dis);

					this.dateCmd = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDCmd, dos);

				// String

				writeString(this.IDClt, dos);

				// String

				writeString(this.IDVend, dos);

				// String

				writeString(this.dateCmd, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDCmd, dos);

				// String

				writeString(this.IDClt, dos);

				// String

				writeString(this.IDVend, dos);

				// String

				writeString(this.dateCmd, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + IDCmd);
			sb.append(",IDClt=" + IDClt);
			sb.append(",IDVend=" + IDVend);
			sb.append(",dateCmd=" + dateCmd);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(CommandeStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class Commande_LIGStruct implements routines.system.IPersistableRow<Commande_LIGStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDCmd;

		public String getIDCmd() {
			return this.IDCmd;
		}

		public String IDPrd;

		public String getIDPrd() {
			return this.IDPrd;
		}

		public String qtt;

		public String getQtt() {
			return this.qtt;
		}

		public String IDCoul;

		public String getIDCoul() {
			return this.IDCoul;
		}

		public String prixVenteInitial;

		public String getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readString(dis);

					this.IDPrd = readString(dis);

					this.qtt = readString(dis);

					this.IDCoul = readString(dis);

					this.prixVenteInitial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readString(dis);

					this.IDPrd = readString(dis);

					this.qtt = readString(dis);

					this.IDCoul = readString(dis);

					this.prixVenteInitial = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDCmd, dos);

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.qtt, dos);

				// String

				writeString(this.IDCoul, dos);

				// String

				writeString(this.prixVenteInitial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDCmd, dos);

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.qtt, dos);

				// String

				writeString(this.IDCoul, dos);

				// String

				writeString(this.prixVenteInitial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + IDCmd);
			sb.append(",IDPrd=" + IDPrd);
			sb.append(",qtt=" + qtt);
			sb.append(",IDCoul=" + IDCoul);
			sb.append(",prixVenteInitial=" + prixVenteInitial);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Commande_LIGStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class ProduitStruct implements routines.system.IPersistableRow<ProduitStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDPrd;

		public String getIDPrd() {
			return this.IDPrd;
		}

		public String nomPrd;

		public String getNomPrd() {
			return this.nomPrd;
		}

		public String paysPrd;

		public String getPaysPrd() {
			return this.paysPrd;
		}

		public String prixPrd;

		public String getPrixPrd() {
			return this.prixPrd;
		}

		public String IDFam;

		public String getIDFam() {
			return this.IDFam;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readString(dis);

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readString(dis);

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// String

				writeString(this.prixPrd, dos);

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// String

				writeString(this.prixPrd, dos);

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDPrd=" + IDPrd);
			sb.append(",nomPrd=" + nomPrd);
			sb.append(",paysPrd=" + paysPrd);
			sb.append(",prixPrd=" + prixPrd);
			sb.append(",IDFam=" + IDFam);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(ProduitStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class FamilleStruct implements routines.system.IPersistableRow<FamilleStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDFam;

		public String getIDFam() {
			return this.IDFam;
		}

		public String Famille;

		public String getFamille() {
			return this.Famille;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDFam = readString(dis);

					this.Famille = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDFam = readString(dis);

					this.Famille = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDFam, dos);

				// String

				writeString(this.Famille, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDFam, dos);

				// String

				writeString(this.Famille, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDFam=" + IDFam);
			sb.append(",Famille=" + Famille);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(FamilleStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class GammeStruct implements routines.system.IPersistableRow<GammeStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String Min;

		public String getMin() {
			return this.Min;
		}

		public String Max;

		public String getMax() {
			return this.Max;
		}

		public String Nom;

		public String getNom() {
			return this.Nom;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.Min = readString(dis);

					this.Max = readString(dis);

					this.Nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.Min = readString(dis);

					this.Max = readString(dis);

					this.Nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Min, dos);

				// String

				writeString(this.Max, dos);

				// String

				writeString(this.Nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Min, dos);

				// String

				writeString(this.Max, dos);

				// String

				writeString(this.Nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Min=" + Min);
			sb.append(",Max=" + Max);
			sb.append(",Nom=" + Nom);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(GammeStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class CouleurStruct implements routines.system.IPersistableRow<CouleurStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDCoul;

		public String getIDCoul() {
			return this.IDCoul;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCoul = readString(dis);

					this.Couleur = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCoul = readString(dis);

					this.Couleur = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDCoul, dos);

				// String

				writeString(this.Couleur, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDCoul, dos);

				// String

				writeString(this.Couleur, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCoul=" + IDCoul);
			sb.append(",Couleur=" + Couleur);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(CouleurStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row2Struct implements routines.system.IPersistableRow<row2Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer _TypeLigne;

		public Integer get_TypeLigne() {
			return this._TypeLigne;
		}

		public String _V1;

		public String get_V1() {
			return this._V1;
		}

		public String _V2;

		public String get_V2() {
			return this._V2;
		}

		public String _V3;

		public String get_V3() {
			return this._V3;
		}

		public String _V4;

		public String get_V4() {
			return this._V4;
		}

		public String _V5;

		public String get_V5() {
			return this._V5;
		}

		public String _V6;

		public String get_V6() {
			return this._V6;
		}

		public String _V7;

		public String get_V7() {
			return this._V7;
		}

		public String _V8;

		public String get_V8() {
			return this._V8;
		}

		public String _V9;

		public String get_V9() {
			return this._V9;
		}

		public String _V10;

		public String get_V10() {
			return this._V10;
		}

		public String _V11;

		public String get_V11() {
			return this._V11;
		}

		public String _V12;

		public String get_V12() {
			return this._V12;
		}

		public String Column13;

		public String getColumn13() {
			return this.Column13;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this._TypeLigne = readInteger(dis);

					this._V1 = readString(dis);

					this._V2 = readString(dis);

					this._V3 = readString(dis);

					this._V4 = readString(dis);

					this._V5 = readString(dis);

					this._V6 = readString(dis);

					this._V7 = readString(dis);

					this._V8 = readString(dis);

					this._V9 = readString(dis);

					this._V10 = readString(dis);

					this._V11 = readString(dis);

					this._V12 = readString(dis);

					this.Column13 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this._TypeLigne = readInteger(dis);

					this._V1 = readString(dis);

					this._V2 = readString(dis);

					this._V3 = readString(dis);

					this._V4 = readString(dis);

					this._V5 = readString(dis);

					this._V6 = readString(dis);

					this._V7 = readString(dis);

					this._V8 = readString(dis);

					this._V9 = readString(dis);

					this._V10 = readString(dis);

					this._V11 = readString(dis);

					this._V12 = readString(dis);

					this.Column13 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this._TypeLigne, dos);

				// String

				writeString(this._V1, dos);

				// String

				writeString(this._V2, dos);

				// String

				writeString(this._V3, dos);

				// String

				writeString(this._V4, dos);

				// String

				writeString(this._V5, dos);

				// String

				writeString(this._V6, dos);

				// String

				writeString(this._V7, dos);

				// String

				writeString(this._V8, dos);

				// String

				writeString(this._V9, dos);

				// String

				writeString(this._V10, dos);

				// String

				writeString(this._V11, dos);

				// String

				writeString(this._V12, dos);

				// String

				writeString(this.Column13, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this._TypeLigne, dos);

				// String

				writeString(this._V1, dos);

				// String

				writeString(this._V2, dos);

				// String

				writeString(this._V3, dos);

				// String

				writeString(this._V4, dos);

				// String

				writeString(this._V5, dos);

				// String

				writeString(this._V6, dos);

				// String

				writeString(this._V7, dos);

				// String

				writeString(this._V8, dos);

				// String

				writeString(this._V9, dos);

				// String

				writeString(this._V10, dos);

				// String

				writeString(this._V11, dos);

				// String

				writeString(this._V12, dos);

				// String

				writeString(this.Column13, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_TypeLigne=" + String.valueOf(_TypeLigne));
			sb.append(",_V1=" + _V1);
			sb.append(",_V2=" + _V2);
			sb.append(",_V3=" + _V3);
			sb.append(",_V4=" + _V4);
			sb.append(",_V5=" + _V5);
			sb.append(",_V6=" + _V6);
			sb.append(",_V7=" + _V7);
			sb.append(",_V8=" + _V8);
			sb.append(",_V9=" + _V9);
			sb.append(",_V10=" + _V10);
			sb.append(",_V11=" + _V11);
			sb.append(",_V12=" + _V12);
			sb.append(",Column13=" + Column13);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer _TypeLigne;

		public Integer get_TypeLigne() {
			return this._TypeLigne;
		}

		public String _V1;

		public String get_V1() {
			return this._V1;
		}

		public String _V2;

		public String get_V2() {
			return this._V2;
		}

		public String _V3;

		public String get_V3() {
			return this._V3;
		}

		public String _V4;

		public String get_V4() {
			return this._V4;
		}

		public String _V5;

		public String get_V5() {
			return this._V5;
		}

		public String _V6;

		public String get_V6() {
			return this._V6;
		}

		public String _V7;

		public String get_V7() {
			return this._V7;
		}

		public String _V8;

		public String get_V8() {
			return this._V8;
		}

		public String _V9;

		public String get_V9() {
			return this._V9;
		}

		public String _V10;

		public String get_V10() {
			return this._V10;
		}

		public String _V11;

		public String get_V11() {
			return this._V11;
		}

		public String _V12;

		public String get_V12() {
			return this._V12;
		}

		public String Column13;

		public String getColumn13() {
			return this.Column13;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this._TypeLigne = readInteger(dis);

					this._V1 = readString(dis);

					this._V2 = readString(dis);

					this._V3 = readString(dis);

					this._V4 = readString(dis);

					this._V5 = readString(dis);

					this._V6 = readString(dis);

					this._V7 = readString(dis);

					this._V8 = readString(dis);

					this._V9 = readString(dis);

					this._V10 = readString(dis);

					this._V11 = readString(dis);

					this._V12 = readString(dis);

					this.Column13 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this._TypeLigne = readInteger(dis);

					this._V1 = readString(dis);

					this._V2 = readString(dis);

					this._V3 = readString(dis);

					this._V4 = readString(dis);

					this._V5 = readString(dis);

					this._V6 = readString(dis);

					this._V7 = readString(dis);

					this._V8 = readString(dis);

					this._V9 = readString(dis);

					this._V10 = readString(dis);

					this._V11 = readString(dis);

					this._V12 = readString(dis);

					this.Column13 = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this._TypeLigne, dos);

				// String

				writeString(this._V1, dos);

				// String

				writeString(this._V2, dos);

				// String

				writeString(this._V3, dos);

				// String

				writeString(this._V4, dos);

				// String

				writeString(this._V5, dos);

				// String

				writeString(this._V6, dos);

				// String

				writeString(this._V7, dos);

				// String

				writeString(this._V8, dos);

				// String

				writeString(this._V9, dos);

				// String

				writeString(this._V10, dos);

				// String

				writeString(this._V11, dos);

				// String

				writeString(this._V12, dos);

				// String

				writeString(this.Column13, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this._TypeLigne, dos);

				// String

				writeString(this._V1, dos);

				// String

				writeString(this._V2, dos);

				// String

				writeString(this._V3, dos);

				// String

				writeString(this._V4, dos);

				// String

				writeString(this._V5, dos);

				// String

				writeString(this._V6, dos);

				// String

				writeString(this._V7, dos);

				// String

				writeString(this._V8, dos);

				// String

				writeString(this._V9, dos);

				// String

				writeString(this._V10, dos);

				// String

				writeString(this._V11, dos);

				// String

				writeString(this._V12, dos);

				// String

				writeString(this.Column13, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("_TypeLigne=" + String.valueOf(_TypeLigne));
			sb.append(",_V1=" + _V1);
			sb.append(",_V2=" + _V2);
			sb.append(",_V3=" + _V3);
			sb.append(",_V4=" + _V4);
			sb.append(",_V5=" + _V5);
			sb.append(",_V6=" + _V6);
			sb.append(",_V7=" + _V7);
			sb.append(",_V8=" + _V8);
			sb.append(",_V9=" + _V9);
			sb.append(",_V10=" + _V10);
			sb.append(",_V11=" + _V11);
			sb.append(",_V12=" + _V12);
			sb.append(",Column13=" + Column13);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				row1Struct row2 = row1;
				ClientStruct Client = new ClientStruct();
				VendeurStruct Vendeur = new VendeurStruct();
				CommandeStruct Commande = new CommandeStruct();
				Commande_LIGStruct Commande_LIG = new Commande_LIGStruct();
				ProduitStruct Produit = new ProduitStruct();
				FamilleStruct Famille = new FamilleStruct();
				GammeStruct Gamme = new GammeStruct();
				CouleurStruct Couleur = new CouleurStruct();

				/**
				 * [tFileOutputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_1", false);
				start_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Client");
				}

				int tos_count_tFileOutputDelimited_1 = 0;

				String fileName_tFileOutputDelimited_1 = "";
				fileName_tFileOutputDelimited_1 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Clients.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_1 = null;
				String extension_tFileOutputDelimited_1 = null;
				String directory_tFileOutputDelimited_1 = null;
				if ((fileName_tFileOutputDelimited_1.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") < fileName_tFileOutputDelimited_1
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
							fileName_tFileOutputDelimited_1.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_1.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1.substring(0,
								fileName_tFileOutputDelimited_1.lastIndexOf("."));
						extension_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1
								.substring(fileName_tFileOutputDelimited_1.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_1 = fileName_tFileOutputDelimited_1;
						extension_tFileOutputDelimited_1 = "";
					}
					directory_tFileOutputDelimited_1 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_1 = true;
				java.io.File filetFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);
				int nb_line_tFileOutputDelimited_1 = 0;
				int splitedFileNo_tFileOutputDelimited_1 = 0;
				int currentRow_tFileOutputDelimited_1 = 0;

				final String OUT_DELIM_tFileOutputDelimited_1 = /** Start field tFileOutputDelimited_1:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_1:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_1 = /**
																		 * Start field
																		 * tFileOutputDelimited_1:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_1:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_1 != null && directory_tFileOutputDelimited_1.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_1 = new java.io.File(directory_tFileOutputDelimited_1);
					if (!dir_tFileOutputDelimited_1.exists()) {
						dir_tFileOutputDelimited_1.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_1 = null;

				java.io.File fileToDelete_tFileOutputDelimited_1 = new java.io.File(fileName_tFileOutputDelimited_1);
				if (fileToDelete_tFileOutputDelimited_1.exists()) {
					fileToDelete_tFileOutputDelimited_1.delete();
				}
				outtFileOutputDelimited_1 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_1, false), "UTF-8"));
				if (filetFileOutputDelimited_1.length() == 0) {
					outtFileOutputDelimited_1.write("IDClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("nomClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("prenomClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("adresseClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("villeClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("etatClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("_V11");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("depClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("regionClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("paysClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.write("telClt");
					outtFileOutputDelimited_1.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);
					outtFileOutputDelimited_1.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_1", outtFileOutputDelimited_1);
				resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

				/**
				 * [tFileOutputDelimited_1 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_2", false);
				start_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Vendeur");
				}

				int tos_count_tFileOutputDelimited_2 = 0;

				String fileName_tFileOutputDelimited_2 = "";
				fileName_tFileOutputDelimited_2 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Vendeurs.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_2 = null;
				String extension_tFileOutputDelimited_2 = null;
				String directory_tFileOutputDelimited_2 = null;
				if ((fileName_tFileOutputDelimited_2.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") < fileName_tFileOutputDelimited_2
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
							fileName_tFileOutputDelimited_2.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_2.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2.substring(0,
								fileName_tFileOutputDelimited_2.lastIndexOf("."));
						extension_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2
								.substring(fileName_tFileOutputDelimited_2.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_2 = fileName_tFileOutputDelimited_2;
						extension_tFileOutputDelimited_2 = "";
					}
					directory_tFileOutputDelimited_2 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_2 = true;
				java.io.File filetFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);
				int nb_line_tFileOutputDelimited_2 = 0;
				int splitedFileNo_tFileOutputDelimited_2 = 0;
				int currentRow_tFileOutputDelimited_2 = 0;

				final String OUT_DELIM_tFileOutputDelimited_2 = /** Start field tFileOutputDelimited_2:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_2:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_2 = /**
																		 * Start field
																		 * tFileOutputDelimited_2:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_2:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_2 != null && directory_tFileOutputDelimited_2.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_2 = new java.io.File(directory_tFileOutputDelimited_2);
					if (!dir_tFileOutputDelimited_2.exists()) {
						dir_tFileOutputDelimited_2.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_2 = null;

				java.io.File fileToDelete_tFileOutputDelimited_2 = new java.io.File(fileName_tFileOutputDelimited_2);
				if (fileToDelete_tFileOutputDelimited_2.exists()) {
					fileToDelete_tFileOutputDelimited_2.delete();
				}
				outtFileOutputDelimited_2 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_2, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_2.length() == 0) {
					outtFileOutputDelimited_2.write("IDVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("nomVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("prenomVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("anneeEmbaucheVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("bureauVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("codeBudgetVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("dateEmbaucheVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("nomCmpltVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("fonctionVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("gradeVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("niveauVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.write("salAnnuelVend");
					outtFileOutputDelimited_2.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);
					outtFileOutputDelimited_2.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_2", outtFileOutputDelimited_2);
				resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

				/**
				 * [tFileOutputDelimited_2 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_3", false);
				start_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Commande");
				}

				int tos_count_tFileOutputDelimited_3 = 0;

				String fileName_tFileOutputDelimited_3 = "";
				fileName_tFileOutputDelimited_3 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commandes.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_3 = null;
				String extension_tFileOutputDelimited_3 = null;
				String directory_tFileOutputDelimited_3 = null;
				if ((fileName_tFileOutputDelimited_3.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") < fileName_tFileOutputDelimited_3
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
							fileName_tFileOutputDelimited_3.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_3.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3.substring(0,
								fileName_tFileOutputDelimited_3.lastIndexOf("."));
						extension_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3
								.substring(fileName_tFileOutputDelimited_3.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_3 = fileName_tFileOutputDelimited_3;
						extension_tFileOutputDelimited_3 = "";
					}
					directory_tFileOutputDelimited_3 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_3 = true;
				java.io.File filetFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);
				int nb_line_tFileOutputDelimited_3 = 0;
				int splitedFileNo_tFileOutputDelimited_3 = 0;
				int currentRow_tFileOutputDelimited_3 = 0;

				final String OUT_DELIM_tFileOutputDelimited_3 = /** Start field tFileOutputDelimited_3:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_3:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_3 = /**
																		 * Start field
																		 * tFileOutputDelimited_3:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_3:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_3 != null && directory_tFileOutputDelimited_3.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_3 = new java.io.File(directory_tFileOutputDelimited_3);
					if (!dir_tFileOutputDelimited_3.exists()) {
						dir_tFileOutputDelimited_3.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_3 = null;

				java.io.File fileToDelete_tFileOutputDelimited_3 = new java.io.File(fileName_tFileOutputDelimited_3);
				if (fileToDelete_tFileOutputDelimited_3.exists()) {
					fileToDelete_tFileOutputDelimited_3.delete();
				}
				outtFileOutputDelimited_3 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_3, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_3.length() == 0) {
					outtFileOutputDelimited_3.write("IDCmd");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("IDClt");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("IDVend");
					outtFileOutputDelimited_3.write(OUT_DELIM_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.write("dateCmd");
					outtFileOutputDelimited_3.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);
					outtFileOutputDelimited_3.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_3", outtFileOutputDelimited_3);
				resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

				/**
				 * [tFileOutputDelimited_3 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_4", false);
				start_Hash.put("tFileOutputDelimited_4", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Commande_LIG");
				}

				int tos_count_tFileOutputDelimited_4 = 0;

				String fileName_tFileOutputDelimited_4 = "";
				fileName_tFileOutputDelimited_4 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_4 = null;
				String extension_tFileOutputDelimited_4 = null;
				String directory_tFileOutputDelimited_4 = null;
				if ((fileName_tFileOutputDelimited_4.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_4.lastIndexOf(".") < fileName_tFileOutputDelimited_4
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4;
						extension_tFileOutputDelimited_4 = "";
					} else {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
								fileName_tFileOutputDelimited_4.lastIndexOf("."));
						extension_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4
								.substring(fileName_tFileOutputDelimited_4.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
							fileName_tFileOutputDelimited_4.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_4.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4.substring(0,
								fileName_tFileOutputDelimited_4.lastIndexOf("."));
						extension_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4
								.substring(fileName_tFileOutputDelimited_4.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_4 = fileName_tFileOutputDelimited_4;
						extension_tFileOutputDelimited_4 = "";
					}
					directory_tFileOutputDelimited_4 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_4 = true;
				java.io.File filetFileOutputDelimited_4 = new java.io.File(fileName_tFileOutputDelimited_4);
				globalMap.put("tFileOutputDelimited_4_FILE_NAME", fileName_tFileOutputDelimited_4);
				int nb_line_tFileOutputDelimited_4 = 0;
				int splitedFileNo_tFileOutputDelimited_4 = 0;
				int currentRow_tFileOutputDelimited_4 = 0;

				final String OUT_DELIM_tFileOutputDelimited_4 = /** Start field tFileOutputDelimited_4:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_4:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_4 = /**
																		 * Start field
																		 * tFileOutputDelimited_4:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_4:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_4 != null && directory_tFileOutputDelimited_4.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_4 = new java.io.File(directory_tFileOutputDelimited_4);
					if (!dir_tFileOutputDelimited_4.exists()) {
						dir_tFileOutputDelimited_4.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_4 = null;

				java.io.File fileToDelete_tFileOutputDelimited_4 = new java.io.File(fileName_tFileOutputDelimited_4);
				if (fileToDelete_tFileOutputDelimited_4.exists()) {
					fileToDelete_tFileOutputDelimited_4.delete();
				}
				outtFileOutputDelimited_4 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_4, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_4.length() == 0) {
					outtFileOutputDelimited_4.write("IDCmd");
					outtFileOutputDelimited_4.write(OUT_DELIM_tFileOutputDelimited_4);
					outtFileOutputDelimited_4.write("IDPrd");
					outtFileOutputDelimited_4.write(OUT_DELIM_tFileOutputDelimited_4);
					outtFileOutputDelimited_4.write("qtt");
					outtFileOutputDelimited_4.write(OUT_DELIM_tFileOutputDelimited_4);
					outtFileOutputDelimited_4.write("IDCoul");
					outtFileOutputDelimited_4.write(OUT_DELIM_tFileOutputDelimited_4);
					outtFileOutputDelimited_4.write("prixVenteInitial");
					outtFileOutputDelimited_4.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_4);
					outtFileOutputDelimited_4.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_4", outtFileOutputDelimited_4);
				resourceMap.put("nb_line_tFileOutputDelimited_4", nb_line_tFileOutputDelimited_4);

				/**
				 * [tFileOutputDelimited_4 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_5 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_5", false);
				start_Hash.put("tFileOutputDelimited_5", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Produit");
				}

				int tos_count_tFileOutputDelimited_5 = 0;

				String fileName_tFileOutputDelimited_5 = "";
				fileName_tFileOutputDelimited_5 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Produits.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_5 = null;
				String extension_tFileOutputDelimited_5 = null;
				String directory_tFileOutputDelimited_5 = null;
				if ((fileName_tFileOutputDelimited_5.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_5.lastIndexOf(".") < fileName_tFileOutputDelimited_5
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5;
						extension_tFileOutputDelimited_5 = "";
					} else {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5.substring(0,
								fileName_tFileOutputDelimited_5.lastIndexOf("."));
						extension_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5
								.substring(fileName_tFileOutputDelimited_5.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5.substring(0,
							fileName_tFileOutputDelimited_5.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_5.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5.substring(0,
								fileName_tFileOutputDelimited_5.lastIndexOf("."));
						extension_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5
								.substring(fileName_tFileOutputDelimited_5.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_5 = fileName_tFileOutputDelimited_5;
						extension_tFileOutputDelimited_5 = "";
					}
					directory_tFileOutputDelimited_5 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_5 = true;
				java.io.File filetFileOutputDelimited_5 = new java.io.File(fileName_tFileOutputDelimited_5);
				globalMap.put("tFileOutputDelimited_5_FILE_NAME", fileName_tFileOutputDelimited_5);
				int nb_line_tFileOutputDelimited_5 = 0;
				int splitedFileNo_tFileOutputDelimited_5 = 0;
				int currentRow_tFileOutputDelimited_5 = 0;

				final String OUT_DELIM_tFileOutputDelimited_5 = /** Start field tFileOutputDelimited_5:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_5:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_5 = /**
																		 * Start field
																		 * tFileOutputDelimited_5:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_5:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_5 != null && directory_tFileOutputDelimited_5.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_5 = new java.io.File(directory_tFileOutputDelimited_5);
					if (!dir_tFileOutputDelimited_5.exists()) {
						dir_tFileOutputDelimited_5.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_5 = null;

				java.io.File fileToDelete_tFileOutputDelimited_5 = new java.io.File(fileName_tFileOutputDelimited_5);
				if (fileToDelete_tFileOutputDelimited_5.exists()) {
					fileToDelete_tFileOutputDelimited_5.delete();
				}
				outtFileOutputDelimited_5 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_5, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_5.length() == 0) {
					outtFileOutputDelimited_5.write("IDPrd");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("nomPrd");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("paysPrd");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("prixPrd");
					outtFileOutputDelimited_5.write(OUT_DELIM_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.write("IDFam");
					outtFileOutputDelimited_5.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_5);
					outtFileOutputDelimited_5.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_5", outtFileOutputDelimited_5);
				resourceMap.put("nb_line_tFileOutputDelimited_5", nb_line_tFileOutputDelimited_5);

				/**
				 * [tFileOutputDelimited_5 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_6 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_6", false);
				start_Hash.put("tFileOutputDelimited_6", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Famille");
				}

				int tos_count_tFileOutputDelimited_6 = 0;

				String fileName_tFileOutputDelimited_6 = "";
				fileName_tFileOutputDelimited_6 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Familles.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_6 = null;
				String extension_tFileOutputDelimited_6 = null;
				String directory_tFileOutputDelimited_6 = null;
				if ((fileName_tFileOutputDelimited_6.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_6.lastIndexOf(".") < fileName_tFileOutputDelimited_6
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6;
						extension_tFileOutputDelimited_6 = "";
					} else {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6.substring(0,
								fileName_tFileOutputDelimited_6.lastIndexOf("."));
						extension_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6
								.substring(fileName_tFileOutputDelimited_6.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6.substring(0,
							fileName_tFileOutputDelimited_6.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_6.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6.substring(0,
								fileName_tFileOutputDelimited_6.lastIndexOf("."));
						extension_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6
								.substring(fileName_tFileOutputDelimited_6.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_6 = fileName_tFileOutputDelimited_6;
						extension_tFileOutputDelimited_6 = "";
					}
					directory_tFileOutputDelimited_6 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_6 = true;
				java.io.File filetFileOutputDelimited_6 = new java.io.File(fileName_tFileOutputDelimited_6);
				globalMap.put("tFileOutputDelimited_6_FILE_NAME", fileName_tFileOutputDelimited_6);
				int nb_line_tFileOutputDelimited_6 = 0;
				int splitedFileNo_tFileOutputDelimited_6 = 0;
				int currentRow_tFileOutputDelimited_6 = 0;

				final String OUT_DELIM_tFileOutputDelimited_6 = /** Start field tFileOutputDelimited_6:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_6:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_6 = /**
																		 * Start field
																		 * tFileOutputDelimited_6:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_6:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_6 != null && directory_tFileOutputDelimited_6.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_6 = new java.io.File(directory_tFileOutputDelimited_6);
					if (!dir_tFileOutputDelimited_6.exists()) {
						dir_tFileOutputDelimited_6.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_6 = null;

				java.io.File fileToDelete_tFileOutputDelimited_6 = new java.io.File(fileName_tFileOutputDelimited_6);
				if (fileToDelete_tFileOutputDelimited_6.exists()) {
					fileToDelete_tFileOutputDelimited_6.delete();
				}
				outtFileOutputDelimited_6 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_6, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_6.length() == 0) {
					outtFileOutputDelimited_6.write("IDFam");
					outtFileOutputDelimited_6.write(OUT_DELIM_tFileOutputDelimited_6);
					outtFileOutputDelimited_6.write("Famille");
					outtFileOutputDelimited_6.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_6);
					outtFileOutputDelimited_6.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_6", outtFileOutputDelimited_6);
				resourceMap.put("nb_line_tFileOutputDelimited_6", nb_line_tFileOutputDelimited_6);

				/**
				 * [tFileOutputDelimited_6 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_8 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_8", false);
				start_Hash.put("tFileOutputDelimited_8", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_8";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Gamme");
				}

				int tos_count_tFileOutputDelimited_8 = 0;

				String fileName_tFileOutputDelimited_8 = "";
				fileName_tFileOutputDelimited_8 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Gammes.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_8 = null;
				String extension_tFileOutputDelimited_8 = null;
				String directory_tFileOutputDelimited_8 = null;
				if ((fileName_tFileOutputDelimited_8.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_8.lastIndexOf(".") < fileName_tFileOutputDelimited_8
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_8 = fileName_tFileOutputDelimited_8;
						extension_tFileOutputDelimited_8 = "";
					} else {
						fullName_tFileOutputDelimited_8 = fileName_tFileOutputDelimited_8.substring(0,
								fileName_tFileOutputDelimited_8.lastIndexOf("."));
						extension_tFileOutputDelimited_8 = fileName_tFileOutputDelimited_8
								.substring(fileName_tFileOutputDelimited_8.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_8 = fileName_tFileOutputDelimited_8.substring(0,
							fileName_tFileOutputDelimited_8.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_8.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_8 = fileName_tFileOutputDelimited_8.substring(0,
								fileName_tFileOutputDelimited_8.lastIndexOf("."));
						extension_tFileOutputDelimited_8 = fileName_tFileOutputDelimited_8
								.substring(fileName_tFileOutputDelimited_8.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_8 = fileName_tFileOutputDelimited_8;
						extension_tFileOutputDelimited_8 = "";
					}
					directory_tFileOutputDelimited_8 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_8 = true;
				java.io.File filetFileOutputDelimited_8 = new java.io.File(fileName_tFileOutputDelimited_8);
				globalMap.put("tFileOutputDelimited_8_FILE_NAME", fileName_tFileOutputDelimited_8);
				int nb_line_tFileOutputDelimited_8 = 0;
				int splitedFileNo_tFileOutputDelimited_8 = 0;
				int currentRow_tFileOutputDelimited_8 = 0;

				final String OUT_DELIM_tFileOutputDelimited_8 = /** Start field tFileOutputDelimited_8:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_8:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_8 = /**
																		 * Start field
																		 * tFileOutputDelimited_8:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_8:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_8 != null && directory_tFileOutputDelimited_8.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_8 = new java.io.File(directory_tFileOutputDelimited_8);
					if (!dir_tFileOutputDelimited_8.exists()) {
						dir_tFileOutputDelimited_8.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_8 = null;

				java.io.File fileToDelete_tFileOutputDelimited_8 = new java.io.File(fileName_tFileOutputDelimited_8);
				if (fileToDelete_tFileOutputDelimited_8.exists()) {
					fileToDelete_tFileOutputDelimited_8.delete();
				}
				outtFileOutputDelimited_8 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_8, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_8.length() == 0) {
					outtFileOutputDelimited_8.write("Min");
					outtFileOutputDelimited_8.write(OUT_DELIM_tFileOutputDelimited_8);
					outtFileOutputDelimited_8.write("Max");
					outtFileOutputDelimited_8.write(OUT_DELIM_tFileOutputDelimited_8);
					outtFileOutputDelimited_8.write("Nom");
					outtFileOutputDelimited_8.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_8);
					outtFileOutputDelimited_8.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_8", outtFileOutputDelimited_8);
				resourceMap.put("nb_line_tFileOutputDelimited_8", nb_line_tFileOutputDelimited_8);

				/**
				 * [tFileOutputDelimited_8 begin ] stop
				 */

				/**
				 * [tFileOutputDelimited_9 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_9", false);
				start_Hash.put("tFileOutputDelimited_9", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_9";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Couleur");
				}

				int tos_count_tFileOutputDelimited_9 = 0;

				String fileName_tFileOutputDelimited_9 = "";
				fileName_tFileOutputDelimited_9 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Couleurs.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_9 = null;
				String extension_tFileOutputDelimited_9 = null;
				String directory_tFileOutputDelimited_9 = null;
				if ((fileName_tFileOutputDelimited_9.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_9.lastIndexOf(".") < fileName_tFileOutputDelimited_9
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_9 = fileName_tFileOutputDelimited_9;
						extension_tFileOutputDelimited_9 = "";
					} else {
						fullName_tFileOutputDelimited_9 = fileName_tFileOutputDelimited_9.substring(0,
								fileName_tFileOutputDelimited_9.lastIndexOf("."));
						extension_tFileOutputDelimited_9 = fileName_tFileOutputDelimited_9
								.substring(fileName_tFileOutputDelimited_9.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_9 = fileName_tFileOutputDelimited_9.substring(0,
							fileName_tFileOutputDelimited_9.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_9.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_9 = fileName_tFileOutputDelimited_9.substring(0,
								fileName_tFileOutputDelimited_9.lastIndexOf("."));
						extension_tFileOutputDelimited_9 = fileName_tFileOutputDelimited_9
								.substring(fileName_tFileOutputDelimited_9.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_9 = fileName_tFileOutputDelimited_9;
						extension_tFileOutputDelimited_9 = "";
					}
					directory_tFileOutputDelimited_9 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_9 = true;
				java.io.File filetFileOutputDelimited_9 = new java.io.File(fileName_tFileOutputDelimited_9);
				globalMap.put("tFileOutputDelimited_9_FILE_NAME", fileName_tFileOutputDelimited_9);
				int nb_line_tFileOutputDelimited_9 = 0;
				int splitedFileNo_tFileOutputDelimited_9 = 0;
				int currentRow_tFileOutputDelimited_9 = 0;

				final String OUT_DELIM_tFileOutputDelimited_9 = /** Start field tFileOutputDelimited_9:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_9:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_9 = /**
																		 * Start field
																		 * tFileOutputDelimited_9:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_9:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_9 != null && directory_tFileOutputDelimited_9.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_9 = new java.io.File(directory_tFileOutputDelimited_9);
					if (!dir_tFileOutputDelimited_9.exists()) {
						dir_tFileOutputDelimited_9.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_9 = null;

				java.io.File fileToDelete_tFileOutputDelimited_9 = new java.io.File(fileName_tFileOutputDelimited_9);
				if (fileToDelete_tFileOutputDelimited_9.exists()) {
					fileToDelete_tFileOutputDelimited_9.delete();
				}
				outtFileOutputDelimited_9 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_9, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_9.length() == 0) {
					outtFileOutputDelimited_9.write("IDCoul");
					outtFileOutputDelimited_9.write(OUT_DELIM_tFileOutputDelimited_9);
					outtFileOutputDelimited_9.write("Couleur");
					outtFileOutputDelimited_9.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_9);
					outtFileOutputDelimited_9.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_9", outtFileOutputDelimited_9);
				resourceMap.put("nb_line_tFileOutputDelimited_9", nb_line_tFileOutputDelimited_9);

				/**
				 * [tFileOutputDelimited_9 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row2");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				ClientStruct Client_tmp = new ClientStruct();
				VendeurStruct Vendeur_tmp = new VendeurStruct();
				CommandeStruct Commande_tmp = new CommandeStruct();
				Commande_LIGStruct Commande_LIG_tmp = new Commande_LIGStruct();
				ProduitStruct Produit_tmp = new ProduitStruct();
				FamilleStruct Famille_tmp = new FamilleStruct();
				GammeStruct Gamme_tmp = new GammeStruct();
				CouleurStruct Couleur_tmp = new CouleurStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tLogRow_1 begin ] start
				 */

				ok_Hash.put("tLogRow_1", false);
				start_Hash.put("tLogRow_1", System.currentTimeMillis());

				currentComponent = "tLogRow_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tLogRow_1 = 0;

				///////////////////////

				class Util_tLogRow_1 {

					String[] des_top = { ".", ".", "-", "+" };

					String[] des_head = { "|=", "=|", "-", "+" };

					String[] des_bottom = { "'", "'", "-", "+" };

					String name = "";

					java.util.List<String[]> list = new java.util.ArrayList<String[]>();

					int[] colLengths = new int[14];

					public void addRow(String[] row) {

						for (int i = 0; i < 14; i++) {
							if (row[i] != null) {
								colLengths[i] = Math.max(colLengths[i], row[i].length());
							}
						}
						list.add(row);
					}

					public void setTableName(String name) {

						this.name = name;
					}

					public StringBuilder format() {

						StringBuilder sb = new StringBuilder();

						sb.append(print(des_top));

						int totals = 0;
						for (int i = 0; i < colLengths.length; i++) {
							totals = totals + colLengths[i];
						}

						// name
						sb.append("|");
						int k = 0;
						for (k = 0; k < (totals + 13 - name.length()) / 2; k++) {
							sb.append(' ');
						}
						sb.append(name);
						for (int i = 0; i < totals + 13 - name.length() - k; i++) {
							sb.append(' ');
						}
						sb.append("|\n");

						// head and rows
						sb.append(print(des_head));
						for (int i = 0; i < list.size(); i++) {

							String[] row = list.get(i);

							java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());

							StringBuilder sbformat = new StringBuilder();
							sbformat.append("|%1$-");
							sbformat.append(colLengths[0]);
							sbformat.append("s");

							sbformat.append("|%2$-");
							sbformat.append(colLengths[1]);
							sbformat.append("s");

							sbformat.append("|%3$-");
							sbformat.append(colLengths[2]);
							sbformat.append("s");

							sbformat.append("|%4$-");
							sbformat.append(colLengths[3]);
							sbformat.append("s");

							sbformat.append("|%5$-");
							sbformat.append(colLengths[4]);
							sbformat.append("s");

							sbformat.append("|%6$-");
							sbformat.append(colLengths[5]);
							sbformat.append("s");

							sbformat.append("|%7$-");
							sbformat.append(colLengths[6]);
							sbformat.append("s");

							sbformat.append("|%8$-");
							sbformat.append(colLengths[7]);
							sbformat.append("s");

							sbformat.append("|%9$-");
							sbformat.append(colLengths[8]);
							sbformat.append("s");

							sbformat.append("|%10$-");
							sbformat.append(colLengths[9]);
							sbformat.append("s");

							sbformat.append("|%11$-");
							sbformat.append(colLengths[10]);
							sbformat.append("s");

							sbformat.append("|%12$-");
							sbformat.append(colLengths[11]);
							sbformat.append("s");

							sbformat.append("|%13$-");
							sbformat.append(colLengths[12]);
							sbformat.append("s");

							sbformat.append("|%14$-");
							sbformat.append(colLengths[13]);
							sbformat.append("s");

							sbformat.append("|\n");

							formatter.format(sbformat.toString(), (Object[]) row);

							sb.append(formatter.toString());
							if (i == 0)
								sb.append(print(des_head)); // print the head
						}

						// end
						sb.append(print(des_bottom));
						return sb;
					}

					private StringBuilder print(String[] fillChars) {
						StringBuilder sb = new StringBuilder();
						// first column
						sb.append(fillChars[0]);
						for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						for (int i = 0; i < colLengths[1] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[2] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[3] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[4] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[5] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[6] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[7] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[8] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[9] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[10] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[11] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);
						for (int i = 0; i < colLengths[12] - fillChars[3].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[3]);

						// last column
						for (int i = 0; i < colLengths[13] - fillChars[1].length() + 1; i++) {
							sb.append(fillChars[2]);
						}
						sb.append(fillChars[1]);
						sb.append("\n");
						return sb;
					}

					public boolean isTableEmpty() {
						if (list.size() > 1)
							return false;
						return true;
					}
				}
				Util_tLogRow_1 util_tLogRow_1 = new Util_tLogRow_1();
				util_tLogRow_1.setTableName("tLogRow_1");
				util_tLogRow_1.addRow(new String[] { "_TypeLigne", "_V1", "_V2", "_V3", "_V4", "_V5", "_V6", "_V7",
						"_V8", "_V9", "_V10", "_V11", "_V12", "Column13", });
				StringBuilder strBuffer_tLogRow_1 = null;
				int nb_line_tLogRow_1 = 0;
///////////////////////    			

				/**
				 * [tLogRow_1 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_1 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_1", false);
				start_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_1";

				int tos_count_tFileInputDelimited_1 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_1 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_1 = 0;
				int footer_tFileInputDelimited_1 = 0;
				int totalLinetFileInputDelimited_1 = 0;
				int limittFileInputDelimited_1 = -1;
				int lastLinetFileInputDelimited_1 = -1;

				char fieldSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Field Separator) by 'context.fs' or
				// 'globalMap.get("fs")'.
				if (((String) ",").length() > 0) {
					fieldSeparator_tFileInputDelimited_1 = ((String) ",").toCharArray();
				} else {
					throw new IllegalArgumentException("Field Separator must be assigned a char.");
				}

				char rowSeparator_tFileInputDelimited_1[] = null;

				// support passing value (property: Row Separator) by 'context.rs' or
				// 'globalMap.get("rs")'.
				if (((String) "\n").length() > 0) {
					rowSeparator_tFileInputDelimited_1 = ((String) "\n").toCharArray();
				} else {
					throw new IllegalArgumentException("Row Separator must be assigned a char.");
				}

				Object filename_tFileInputDelimited_1 = /** Start field tFileInputDelimited_1:FILENAME */
						"C:/Users/LnD/Documents/Formation Data/Qlick - Talend/Etude de cas -  M2I Vehicules/Etude de cas -  M2I Vehicules/MotorsTL.csv"/**
																																						 * End
																																						 * field
																																						 * tFileInputDelimited_1:FILENAME
																																						 */
				;
				com.talend.csv.CSVReader csvReadertFileInputDelimited_1 = null;

				try {

					String[] rowtFileInputDelimited_1 = null;
					int currentLinetFileInputDelimited_1 = 0;
					int outputLinetFileInputDelimited_1 = 0;
					try {// TD110 begin
						if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {

							int footer_value_tFileInputDelimited_1 = 0;
							if (footer_value_tFileInputDelimited_1 > 0) {
								throw new java.lang.Exception(
										"When the input source is a stream,footer shouldn't be bigger than 0.");
							}

							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									(java.io.InputStream) filename_tFileInputDelimited_1,
									fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
						} else {
							csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
									String.valueOf(filename_tFileInputDelimited_1),
									fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
						}

						csvReadertFileInputDelimited_1.setTrimWhitespace(false);
						if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
								&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
							csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

						csvReadertFileInputDelimited_1.setQuoteChar('"');

						csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						if (footer_tFileInputDelimited_1 > 0) {
							for (totalLinetFileInputDelimited_1 = 0; totalLinetFileInputDelimited_1 < 1; totalLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
							csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);
							while (csvReadertFileInputDelimited_1.readNext()) {

								totalLinetFileInputDelimited_1++;

							}
							int lastLineTemptFileInputDelimited_1 = totalLinetFileInputDelimited_1
									- footer_tFileInputDelimited_1 < 0 ? 0
											: totalLinetFileInputDelimited_1 - footer_tFileInputDelimited_1;
							if (lastLinetFileInputDelimited_1 > 0) {
								lastLinetFileInputDelimited_1 = lastLinetFileInputDelimited_1 < lastLineTemptFileInputDelimited_1
										? lastLinetFileInputDelimited_1
										: lastLineTemptFileInputDelimited_1;
							} else {
								lastLinetFileInputDelimited_1 = lastLineTemptFileInputDelimited_1;
							}

							csvReadertFileInputDelimited_1.close();
							if (filename_tFileInputDelimited_1 instanceof java.io.InputStream) {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										(java.io.InputStream) filename_tFileInputDelimited_1,
										fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
							} else {
								csvReadertFileInputDelimited_1 = new com.talend.csv.CSVReader(
										String.valueOf(filename_tFileInputDelimited_1),
										fieldSeparator_tFileInputDelimited_1[0], "UTF-8");
							}
							csvReadertFileInputDelimited_1.setTrimWhitespace(false);
							if ((rowSeparator_tFileInputDelimited_1[0] != '\n')
									&& (rowSeparator_tFileInputDelimited_1[0] != '\r'))
								csvReadertFileInputDelimited_1.setLineEnd("" + rowSeparator_tFileInputDelimited_1[0]);

							csvReadertFileInputDelimited_1.setQuoteChar('"');

							csvReadertFileInputDelimited_1.setEscapeChar(csvReadertFileInputDelimited_1.getQuoteChar());

						}

						if (limittFileInputDelimited_1 != 0) {
							for (currentLinetFileInputDelimited_1 = 0; currentLinetFileInputDelimited_1 < 1; currentLinetFileInputDelimited_1++) {
								csvReadertFileInputDelimited_1.readNext();
							}
						}
						csvReadertFileInputDelimited_1.setSkipEmptyRecords(false);

					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					} // TD110 end

					while (limittFileInputDelimited_1 != 0 && csvReadertFileInputDelimited_1 != null
							&& csvReadertFileInputDelimited_1.readNext()) {
						rowstate_tFileInputDelimited_1.reset();

						rowtFileInputDelimited_1 = csvReadertFileInputDelimited_1.getValues();

						currentLinetFileInputDelimited_1++;

						if (lastLinetFileInputDelimited_1 > -1
								&& currentLinetFileInputDelimited_1 > lastLinetFileInputDelimited_1) {
							break;
						}
						outputLinetFileInputDelimited_1++;
						if (limittFileInputDelimited_1 > 0
								&& outputLinetFileInputDelimited_1 > limittFileInputDelimited_1) {
							break;
						}

						row1 = null;

						boolean whetherReject_tFileInputDelimited_1 = false;
						row1 = new row1Struct();
						try {

							char fieldSeparator_tFileInputDelimited_1_ListType[] = null;
							// support passing value (property: Field Separator) by 'context.fs' or
							// 'globalMap.get("fs")'.
							if (((String) ",").length() > 0) {
								fieldSeparator_tFileInputDelimited_1_ListType = ((String) ",").toCharArray();
							} else {
								throw new IllegalArgumentException("Field Separator must be assigned a char.");
							}
							if (rowtFileInputDelimited_1.length == 1 && ("\015").equals(rowtFileInputDelimited_1[0])) {// empty
																														// line
																														// when
																														// row
																														// separator
																														// is
																														// '\n'

								row1._TypeLigne = null;

								row1._V1 = null;

								row1._V2 = null;

								row1._V3 = null;

								row1._V4 = null;

								row1._V5 = null;

								row1._V6 = null;

								row1._V7 = null;

								row1._V8 = null;

								row1._V9 = null;

								row1._V10 = null;

								row1._V11 = null;

								row1._V12 = null;

								row1.Column13 = null;

							} else {

								int columnIndexWithD_tFileInputDelimited_1 = 0; // Column Index

								columnIndexWithD_tFileInputDelimited_1 = 0;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									if (rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1].length() > 0) {
										try {

											row1._TypeLigne = ParserUtils.parseTo_Integer(
													rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1]);

										} catch (java.lang.Exception ex_tFileInputDelimited_1) {
											globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE",
													ex_tFileInputDelimited_1.getMessage());
											rowstate_tFileInputDelimited_1.setException(new RuntimeException(String
													.format("Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
															"_TypeLigne", "row1",
															rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1],
															ex_tFileInputDelimited_1),
													ex_tFileInputDelimited_1));
										}
									} else {

										row1._TypeLigne = null;

									}

								} else {

									row1._TypeLigne = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 1;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V1 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V1 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 2;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V2 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V2 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 3;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V3 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V3 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 4;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V4 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V4 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 5;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V5 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V5 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 6;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V6 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V6 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 7;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V7 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V7 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 8;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V8 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V8 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 9;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V9 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V9 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 10;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V10 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V10 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 11;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V11 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V11 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 12;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1._V12 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1._V12 = null;

								}

								columnIndexWithD_tFileInputDelimited_1 = 13;

								if (columnIndexWithD_tFileInputDelimited_1 < rowtFileInputDelimited_1.length) {

									row1.Column13 = rowtFileInputDelimited_1[columnIndexWithD_tFileInputDelimited_1];

								} else {

									row1.Column13 = null;

								}

							}

							if (rowstate_tFileInputDelimited_1.getException() != null) {
								throw rowstate_tFileInputDelimited_1.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_1 = true;

							System.err.println(e.getMessage());
							row1 = null;

							globalMap.put("tFileInputDelimited_1_ERROR_MESSAGE", e.getMessage());

						}

						/**
						 * [tFileInputDelimited_1 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_1 main ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						tos_count_tFileInputDelimited_1++;

						/**
						 * [tFileInputDelimited_1 main ] stop
						 */

						/**
						 * [tFileInputDelimited_1 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_begin ] stop
						 */
// Start of branch "row1"
						if (row1 != null) {

							/**
							 * [tLogRow_1 main ] start
							 */

							currentComponent = "tLogRow_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row1"

								);
							}

///////////////////////		

							String[] row_tLogRow_1 = new String[14];

							if (row1._TypeLigne != null) { //
								row_tLogRow_1[0] = String.valueOf(row1._TypeLigne);

							} //

							if (row1._V1 != null) { //
								row_tLogRow_1[1] = String.valueOf(row1._V1);

							} //

							if (row1._V2 != null) { //
								row_tLogRow_1[2] = String.valueOf(row1._V2);

							} //

							if (row1._V3 != null) { //
								row_tLogRow_1[3] = String.valueOf(row1._V3);

							} //

							if (row1._V4 != null) { //
								row_tLogRow_1[4] = String.valueOf(row1._V4);

							} //

							if (row1._V5 != null) { //
								row_tLogRow_1[5] = String.valueOf(row1._V5);

							} //

							if (row1._V6 != null) { //
								row_tLogRow_1[6] = String.valueOf(row1._V6);

							} //

							if (row1._V7 != null) { //
								row_tLogRow_1[7] = String.valueOf(row1._V7);

							} //

							if (row1._V8 != null) { //
								row_tLogRow_1[8] = String.valueOf(row1._V8);

							} //

							if (row1._V9 != null) { //
								row_tLogRow_1[9] = String.valueOf(row1._V9);

							} //

							if (row1._V10 != null) { //
								row_tLogRow_1[10] = String.valueOf(row1._V10);

							} //

							if (row1._V11 != null) { //
								row_tLogRow_1[11] = String.valueOf(row1._V11);

							} //

							if (row1._V12 != null) { //
								row_tLogRow_1[12] = String.valueOf(row1._V12);

							} //

							if (row1.Column13 != null) { //
								row_tLogRow_1[13] = String.valueOf(row1.Column13);

							} //

							util_tLogRow_1.addRow(row_tLogRow_1);
							nb_line_tLogRow_1++;
//////

//////                    

///////////////////////    			

							row2 = row1;

							tos_count_tLogRow_1++;

							/**
							 * [tLogRow_1 main ] stop
							 */

							/**
							 * [tLogRow_1 process_data_begin ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_begin ] stop
							 */

							/**
							 * [tMap_1 main ] start
							 */

							currentComponent = "tMap_1";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row2"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_1 = false;
							boolean mainRowRejected_tMap_1 = false;

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
								// ###############################
								// # Output tables

								Client = null;
								Vendeur = null;
								Commande = null;
								Commande_LIG = null;
								Produit = null;
								Famille = null;
								Gamme = null;
								Couleur = null;

// # Output table : 'Client'
// # Filter conditions 
								if (

								row2._TypeLigne == 100

								) {
									Client_tmp.IDClt = row2._V1;
									Client_tmp.nomClt = row2._V2;
									Client_tmp.prenomClt = row2._V3;
									Client_tmp.adresseClt = row2._V4;
									Client_tmp.villeClt = row2._V5;
									Client_tmp.etatClt = row2._V6;
									Client_tmp._V11 = row2._V11.equals("USA")
											? row2._V4 + " - " + row2._V5 + " - " + row2._V6
											: "";
									Client_tmp.depClt = row2._V7;
									Client_tmp.regionClt = row2._V10.replaceAll("ÃŽ", "Î");
									Client_tmp.paysClt = row2._V11;
									Client_tmp.telClt = row2._V9;
									Client = Client_tmp;
								} // closing filter/reject

// # Output table : 'Vendeur'
// # Filter conditions 
								if (

								row2._TypeLigne == 200

								) {
									Vendeur_tmp.IDVend = row2._V1;
									Vendeur_tmp.nomVend = row2._V2;
									Vendeur_tmp.prenomVend = row2._V3;
									Vendeur_tmp.anneeEmbaucheVend = row2._V4;
									Vendeur_tmp.bureauVend = row2._V5;
									Vendeur_tmp.codeBudgetVend = row2._V6;
									Vendeur_tmp.dateEmbaucheVend = row2._V7;
									Vendeur_tmp.nomCmpltVend = row2._V8;
									Vendeur_tmp.fonctionVend = row2._V9;
									Vendeur_tmp.gradeVend = row2._V10;
									Vendeur_tmp.niveauVend = row2._V11;
									Vendeur_tmp.salAnnuelVend = row2._V12;
									Vendeur = Vendeur_tmp;
								} // closing filter/reject

// # Output table : 'Commande'
// # Filter conditions 
								if (

								row2._TypeLigne == 300

								) {
									Commande_tmp.IDCmd = row2._V1;
									Commande_tmp.IDClt = row2._V2;
									Commande_tmp.IDVend = row2._V3;
									Commande_tmp.dateCmd = row2._V4;
									Commande = Commande_tmp;
								} // closing filter/reject

// # Output table : 'Commande_LIG'
// # Filter conditions 
								if (

								row2._TypeLigne == 301

								) {
									Commande_LIG_tmp.IDCmd = row2._V1;
									Commande_LIG_tmp.IDPrd = row2._V2;
									Commande_LIG_tmp.qtt = row2._V3;
									Commande_LIG_tmp.IDCoul = row2._V4;
									Commande_LIG_tmp.prixVenteInitial = row2._V5;
									Commande_LIG = Commande_LIG_tmp;
								} // closing filter/reject

// # Output table : 'Produit'
// # Filter conditions 
								if (

								row2._TypeLigne == 400

								) {
									Produit_tmp.IDPrd = row2._V1;
									Produit_tmp.nomPrd = row2._V2;
									Produit_tmp.paysPrd = row2._V3;
									Produit_tmp.prixPrd = row2._V4;
									Produit_tmp.IDFam = row2._V5;
									Produit = Produit_tmp;
								} // closing filter/reject

// # Output table : 'Famille'
// # Filter conditions 
								if (

								row2._TypeLigne == 401

								) {
									Famille_tmp.IDFam = row2._V1;
									Famille_tmp.Famille = row2._V2;
									Famille = Famille_tmp;
								} // closing filter/reject

// # Output table : 'Gamme'
// # Filter conditions 
								if (

								row2._TypeLigne == 402

								) {
									Gamme_tmp.Min = row2._V1;
									Gamme_tmp.Max = row2._V2;
									Gamme_tmp.Nom = row2._V3;
									Gamme = Gamme_tmp;
								} // closing filter/reject

// # Output table : 'Couleur'
// # Filter conditions 
								if (

								row2._TypeLigne == 302

								) {
									Couleur_tmp.IDCoul = row2._V1;
									Couleur_tmp.Couleur = row2._V2;
									Couleur = Couleur_tmp;
								} // closing filter/reject
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_1 = false;

							tos_count_tMap_1++;

							/**
							 * [tMap_1 main ] stop
							 */

							/**
							 * [tMap_1 process_data_begin ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_begin ] stop
							 */
// Start of branch "Client"
							if (Client != null) {

								/**
								 * [tFileOutputDelimited_1 main ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Client"

									);
								}

								StringBuilder sb_tFileOutputDelimited_1 = new StringBuilder();
								if (Client.IDClt != null) {
									sb_tFileOutputDelimited_1.append(Client.IDClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.nomClt != null) {
									sb_tFileOutputDelimited_1.append(Client.nomClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.prenomClt != null) {
									sb_tFileOutputDelimited_1.append(Client.prenomClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.adresseClt != null) {
									sb_tFileOutputDelimited_1.append(Client.adresseClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.villeClt != null) {
									sb_tFileOutputDelimited_1.append(Client.villeClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.etatClt != null) {
									sb_tFileOutputDelimited_1.append(Client.etatClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client._V11 != null) {
									sb_tFileOutputDelimited_1.append(Client._V11);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.depClt != null) {
									sb_tFileOutputDelimited_1.append(Client.depClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.regionClt != null) {
									sb_tFileOutputDelimited_1.append(Client.regionClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.paysClt != null) {
									sb_tFileOutputDelimited_1.append(Client.paysClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_tFileOutputDelimited_1);
								if (Client.telClt != null) {
									sb_tFileOutputDelimited_1.append(Client.telClt);
								}
								sb_tFileOutputDelimited_1.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_1);

								nb_line_tFileOutputDelimited_1++;
								resourceMap.put("nb_line_tFileOutputDelimited_1", nb_line_tFileOutputDelimited_1);

								outtFileOutputDelimited_1.write(sb_tFileOutputDelimited_1.toString());

								tos_count_tFileOutputDelimited_1++;

								/**
								 * [tFileOutputDelimited_1 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_1 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_1";

								/**
								 * [tFileOutputDelimited_1 process_data_end ] stop
								 */

							} // End of branch "Client"

// Start of branch "Vendeur"
							if (Vendeur != null) {

								/**
								 * [tFileOutputDelimited_2 main ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Vendeur"

									);
								}

								StringBuilder sb_tFileOutputDelimited_2 = new StringBuilder();
								if (Vendeur.IDVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.IDVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.nomVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.nomVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.prenomVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.prenomVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.anneeEmbaucheVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.anneeEmbaucheVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.bureauVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.bureauVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.codeBudgetVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.codeBudgetVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.dateEmbaucheVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.dateEmbaucheVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.nomCmpltVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.nomCmpltVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.fonctionVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.fonctionVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.gradeVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.gradeVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.niveauVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.niveauVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_tFileOutputDelimited_2);
								if (Vendeur.salAnnuelVend != null) {
									sb_tFileOutputDelimited_2.append(Vendeur.salAnnuelVend);
								}
								sb_tFileOutputDelimited_2.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_2);

								nb_line_tFileOutputDelimited_2++;
								resourceMap.put("nb_line_tFileOutputDelimited_2", nb_line_tFileOutputDelimited_2);

								outtFileOutputDelimited_2.write(sb_tFileOutputDelimited_2.toString());

								tos_count_tFileOutputDelimited_2++;

								/**
								 * [tFileOutputDelimited_2 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								/**
								 * [tFileOutputDelimited_2 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_2 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_2";

								/**
								 * [tFileOutputDelimited_2 process_data_end ] stop
								 */

							} // End of branch "Vendeur"

// Start of branch "Commande"
							if (Commande != null) {

								/**
								 * [tFileOutputDelimited_3 main ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Commande"

									);
								}

								StringBuilder sb_tFileOutputDelimited_3 = new StringBuilder();
								if (Commande.IDCmd != null) {
									sb_tFileOutputDelimited_3.append(Commande.IDCmd);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (Commande.IDClt != null) {
									sb_tFileOutputDelimited_3.append(Commande.IDClt);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (Commande.IDVend != null) {
									sb_tFileOutputDelimited_3.append(Commande.IDVend);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_tFileOutputDelimited_3);
								if (Commande.dateCmd != null) {
									sb_tFileOutputDelimited_3.append(Commande.dateCmd);
								}
								sb_tFileOutputDelimited_3.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_3);

								nb_line_tFileOutputDelimited_3++;
								resourceMap.put("nb_line_tFileOutputDelimited_3", nb_line_tFileOutputDelimited_3);

								outtFileOutputDelimited_3.write(sb_tFileOutputDelimited_3.toString());

								tos_count_tFileOutputDelimited_3++;

								/**
								 * [tFileOutputDelimited_3 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_3 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								/**
								 * [tFileOutputDelimited_3 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_3 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_3";

								/**
								 * [tFileOutputDelimited_3 process_data_end ] stop
								 */

							} // End of branch "Commande"

// Start of branch "Commande_LIG"
							if (Commande_LIG != null) {

								/**
								 * [tFileOutputDelimited_4 main ] start
								 */

								currentComponent = "tFileOutputDelimited_4";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Commande_LIG"

									);
								}

								StringBuilder sb_tFileOutputDelimited_4 = new StringBuilder();
								if (Commande_LIG.IDCmd != null) {
									sb_tFileOutputDelimited_4.append(Commande_LIG.IDCmd);
								}
								sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
								if (Commande_LIG.IDPrd != null) {
									sb_tFileOutputDelimited_4.append(Commande_LIG.IDPrd);
								}
								sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
								if (Commande_LIG.qtt != null) {
									sb_tFileOutputDelimited_4.append(Commande_LIG.qtt);
								}
								sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
								if (Commande_LIG.IDCoul != null) {
									sb_tFileOutputDelimited_4.append(Commande_LIG.IDCoul);
								}
								sb_tFileOutputDelimited_4.append(OUT_DELIM_tFileOutputDelimited_4);
								if (Commande_LIG.prixVenteInitial != null) {
									sb_tFileOutputDelimited_4.append(Commande_LIG.prixVenteInitial);
								}
								sb_tFileOutputDelimited_4.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_4);

								nb_line_tFileOutputDelimited_4++;
								resourceMap.put("nb_line_tFileOutputDelimited_4", nb_line_tFileOutputDelimited_4);

								outtFileOutputDelimited_4.write(sb_tFileOutputDelimited_4.toString());

								tos_count_tFileOutputDelimited_4++;

								/**
								 * [tFileOutputDelimited_4 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_4 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_4";

								/**
								 * [tFileOutputDelimited_4 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_4 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_4";

								/**
								 * [tFileOutputDelimited_4 process_data_end ] stop
								 */

							} // End of branch "Commande_LIG"

// Start of branch "Produit"
							if (Produit != null) {

								/**
								 * [tFileOutputDelimited_5 main ] start
								 */

								currentComponent = "tFileOutputDelimited_5";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Produit"

									);
								}

								StringBuilder sb_tFileOutputDelimited_5 = new StringBuilder();
								if (Produit.IDPrd != null) {
									sb_tFileOutputDelimited_5.append(Produit.IDPrd);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (Produit.nomPrd != null) {
									sb_tFileOutputDelimited_5.append(Produit.nomPrd);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (Produit.paysPrd != null) {
									sb_tFileOutputDelimited_5.append(Produit.paysPrd);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (Produit.prixPrd != null) {
									sb_tFileOutputDelimited_5.append(Produit.prixPrd);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_tFileOutputDelimited_5);
								if (Produit.IDFam != null) {
									sb_tFileOutputDelimited_5.append(Produit.IDFam);
								}
								sb_tFileOutputDelimited_5.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_5);

								nb_line_tFileOutputDelimited_5++;
								resourceMap.put("nb_line_tFileOutputDelimited_5", nb_line_tFileOutputDelimited_5);

								outtFileOutputDelimited_5.write(sb_tFileOutputDelimited_5.toString());

								tos_count_tFileOutputDelimited_5++;

								/**
								 * [tFileOutputDelimited_5 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_5 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_5";

								/**
								 * [tFileOutputDelimited_5 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_5 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_5";

								/**
								 * [tFileOutputDelimited_5 process_data_end ] stop
								 */

							} // End of branch "Produit"

// Start of branch "Famille"
							if (Famille != null) {

								/**
								 * [tFileOutputDelimited_6 main ] start
								 */

								currentComponent = "tFileOutputDelimited_6";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Famille"

									);
								}

								StringBuilder sb_tFileOutputDelimited_6 = new StringBuilder();
								if (Famille.IDFam != null) {
									sb_tFileOutputDelimited_6.append(Famille.IDFam);
								}
								sb_tFileOutputDelimited_6.append(OUT_DELIM_tFileOutputDelimited_6);
								if (Famille.Famille != null) {
									sb_tFileOutputDelimited_6.append(Famille.Famille);
								}
								sb_tFileOutputDelimited_6.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_6);

								nb_line_tFileOutputDelimited_6++;
								resourceMap.put("nb_line_tFileOutputDelimited_6", nb_line_tFileOutputDelimited_6);

								outtFileOutputDelimited_6.write(sb_tFileOutputDelimited_6.toString());

								tos_count_tFileOutputDelimited_6++;

								/**
								 * [tFileOutputDelimited_6 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_6 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_6";

								/**
								 * [tFileOutputDelimited_6 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_6 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_6";

								/**
								 * [tFileOutputDelimited_6 process_data_end ] stop
								 */

							} // End of branch "Famille"

// Start of branch "Gamme"
							if (Gamme != null) {

								/**
								 * [tFileOutputDelimited_8 main ] start
								 */

								currentComponent = "tFileOutputDelimited_8";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Gamme"

									);
								}

								StringBuilder sb_tFileOutputDelimited_8 = new StringBuilder();
								if (Gamme.Min != null) {
									sb_tFileOutputDelimited_8.append(Gamme.Min);
								}
								sb_tFileOutputDelimited_8.append(OUT_DELIM_tFileOutputDelimited_8);
								if (Gamme.Max != null) {
									sb_tFileOutputDelimited_8.append(Gamme.Max);
								}
								sb_tFileOutputDelimited_8.append(OUT_DELIM_tFileOutputDelimited_8);
								if (Gamme.Nom != null) {
									sb_tFileOutputDelimited_8.append(Gamme.Nom);
								}
								sb_tFileOutputDelimited_8.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_8);

								nb_line_tFileOutputDelimited_8++;
								resourceMap.put("nb_line_tFileOutputDelimited_8", nb_line_tFileOutputDelimited_8);

								outtFileOutputDelimited_8.write(sb_tFileOutputDelimited_8.toString());

								tos_count_tFileOutputDelimited_8++;

								/**
								 * [tFileOutputDelimited_8 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_8 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_8";

								/**
								 * [tFileOutputDelimited_8 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_8 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_8";

								/**
								 * [tFileOutputDelimited_8 process_data_end ] stop
								 */

							} // End of branch "Gamme"

// Start of branch "Couleur"
							if (Couleur != null) {

								/**
								 * [tFileOutputDelimited_9 main ] start
								 */

								currentComponent = "tFileOutputDelimited_9";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Couleur"

									);
								}

								StringBuilder sb_tFileOutputDelimited_9 = new StringBuilder();
								if (Couleur.IDCoul != null) {
									sb_tFileOutputDelimited_9.append(Couleur.IDCoul);
								}
								sb_tFileOutputDelimited_9.append(OUT_DELIM_tFileOutputDelimited_9);
								if (Couleur.Couleur != null) {
									sb_tFileOutputDelimited_9.append(Couleur.Couleur);
								}
								sb_tFileOutputDelimited_9.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_9);

								nb_line_tFileOutputDelimited_9++;
								resourceMap.put("nb_line_tFileOutputDelimited_9", nb_line_tFileOutputDelimited_9);

								outtFileOutputDelimited_9.write(sb_tFileOutputDelimited_9.toString());

								tos_count_tFileOutputDelimited_9++;

								/**
								 * [tFileOutputDelimited_9 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_9 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_9";

								/**
								 * [tFileOutputDelimited_9 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_9 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_9";

								/**
								 * [tFileOutputDelimited_9 process_data_end ] stop
								 */

							} // End of branch "Couleur"

							/**
							 * [tMap_1 process_data_end ] start
							 */

							currentComponent = "tMap_1";

							/**
							 * [tMap_1 process_data_end ] stop
							 */

							/**
							 * [tLogRow_1 process_data_end ] start
							 */

							currentComponent = "tLogRow_1";

							/**
							 * [tLogRow_1 process_data_end ] stop
							 */

						} // End of branch "row1"

						/**
						 * [tFileInputDelimited_1 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						/**
						 * [tFileInputDelimited_1 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_1 end ] start
						 */

						currentComponent = "tFileInputDelimited_1";

						nb_line_tFileInputDelimited_1++;
					}

				} finally {
					if (!(filename_tFileInputDelimited_1 instanceof java.io.InputStream)) {
						if (csvReadertFileInputDelimited_1 != null) {
							csvReadertFileInputDelimited_1.close();
						}
					}
					if (csvReadertFileInputDelimited_1 != null) {
						globalMap.put("tFileInputDelimited_1_NB_LINE", nb_line_tFileInputDelimited_1);
					}

				}

				ok_Hash.put("tFileInputDelimited_1", true);
				end_Hash.put("tFileInputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_1 end ] stop
				 */

				/**
				 * [tLogRow_1 end ] start
				 */

				currentComponent = "tLogRow_1";

//////

				java.io.PrintStream consoleOut_tLogRow_1 = null;
				if (globalMap.get("tLogRow_CONSOLE") != null) {
					consoleOut_tLogRow_1 = (java.io.PrintStream) globalMap.get("tLogRow_CONSOLE");
				} else {
					consoleOut_tLogRow_1 = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));
					globalMap.put("tLogRow_CONSOLE", consoleOut_tLogRow_1);
				}

				consoleOut_tLogRow_1.println(util_tLogRow_1.format().toString());
				consoleOut_tLogRow_1.flush();
//////
				globalMap.put("tLogRow_1_NB_LINE", nb_line_tLogRow_1);

///////////////////////    			

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tLogRow_1", true);
				end_Hash.put("tLogRow_1", System.currentTimeMillis());

				/**
				 * [tLogRow_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row2");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 end ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (outtFileOutputDelimited_1 != null) {
					outtFileOutputDelimited_1.flush();
					outtFileOutputDelimited_1.close();
				}

				globalMap.put("tFileOutputDelimited_1_NB_LINE", nb_line_tFileOutputDelimited_1);
				globalMap.put("tFileOutputDelimited_1_FILE_NAME", fileName_tFileOutputDelimited_1);

				resourceMap.put("finish_tFileOutputDelimited_1", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Client");
				}

				ok_Hash.put("tFileOutputDelimited_1", true);
				end_Hash.put("tFileOutputDelimited_1", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_1 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 end ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (outtFileOutputDelimited_2 != null) {
					outtFileOutputDelimited_2.flush();
					outtFileOutputDelimited_2.close();
				}

				globalMap.put("tFileOutputDelimited_2_NB_LINE", nb_line_tFileOutputDelimited_2);
				globalMap.put("tFileOutputDelimited_2_FILE_NAME", fileName_tFileOutputDelimited_2);

				resourceMap.put("finish_tFileOutputDelimited_2", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Vendeur");
				}

				ok_Hash.put("tFileOutputDelimited_2", true);
				end_Hash.put("tFileOutputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_2 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 end ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (outtFileOutputDelimited_3 != null) {
					outtFileOutputDelimited_3.flush();
					outtFileOutputDelimited_3.close();
				}

				globalMap.put("tFileOutputDelimited_3_NB_LINE", nb_line_tFileOutputDelimited_3);
				globalMap.put("tFileOutputDelimited_3_FILE_NAME", fileName_tFileOutputDelimited_3);

				resourceMap.put("finish_tFileOutputDelimited_3", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Commande");
				}

				ok_Hash.put("tFileOutputDelimited_3", true);
				end_Hash.put("tFileOutputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_3 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 end ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				if (outtFileOutputDelimited_4 != null) {
					outtFileOutputDelimited_4.flush();
					outtFileOutputDelimited_4.close();
				}

				globalMap.put("tFileOutputDelimited_4_NB_LINE", nb_line_tFileOutputDelimited_4);
				globalMap.put("tFileOutputDelimited_4_FILE_NAME", fileName_tFileOutputDelimited_4);

				resourceMap.put("finish_tFileOutputDelimited_4", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Commande_LIG");
				}

				ok_Hash.put("tFileOutputDelimited_4", true);
				end_Hash.put("tFileOutputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_4 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_5 end ] start
				 */

				currentComponent = "tFileOutputDelimited_5";

				if (outtFileOutputDelimited_5 != null) {
					outtFileOutputDelimited_5.flush();
					outtFileOutputDelimited_5.close();
				}

				globalMap.put("tFileOutputDelimited_5_NB_LINE", nb_line_tFileOutputDelimited_5);
				globalMap.put("tFileOutputDelimited_5_FILE_NAME", fileName_tFileOutputDelimited_5);

				resourceMap.put("finish_tFileOutputDelimited_5", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Produit");
				}

				ok_Hash.put("tFileOutputDelimited_5", true);
				end_Hash.put("tFileOutputDelimited_5", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_5 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_6 end ] start
				 */

				currentComponent = "tFileOutputDelimited_6";

				if (outtFileOutputDelimited_6 != null) {
					outtFileOutputDelimited_6.flush();
					outtFileOutputDelimited_6.close();
				}

				globalMap.put("tFileOutputDelimited_6_NB_LINE", nb_line_tFileOutputDelimited_6);
				globalMap.put("tFileOutputDelimited_6_FILE_NAME", fileName_tFileOutputDelimited_6);

				resourceMap.put("finish_tFileOutputDelimited_6", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Famille");
				}

				ok_Hash.put("tFileOutputDelimited_6", true);
				end_Hash.put("tFileOutputDelimited_6", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_6 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_8 end ] start
				 */

				currentComponent = "tFileOutputDelimited_8";

				if (outtFileOutputDelimited_8 != null) {
					outtFileOutputDelimited_8.flush();
					outtFileOutputDelimited_8.close();
				}

				globalMap.put("tFileOutputDelimited_8_NB_LINE", nb_line_tFileOutputDelimited_8);
				globalMap.put("tFileOutputDelimited_8_FILE_NAME", fileName_tFileOutputDelimited_8);

				resourceMap.put("finish_tFileOutputDelimited_8", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Gamme");
				}

				ok_Hash.put("tFileOutputDelimited_8", true);
				end_Hash.put("tFileOutputDelimited_8", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_8 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_9 end ] start
				 */

				currentComponent = "tFileOutputDelimited_9";

				if (outtFileOutputDelimited_9 != null) {
					outtFileOutputDelimited_9.flush();
					outtFileOutputDelimited_9.close();
				}

				globalMap.put("tFileOutputDelimited_9_NB_LINE", nb_line_tFileOutputDelimited_9);
				globalMap.put("tFileOutputDelimited_9_FILE_NAME", fileName_tFileOutputDelimited_9);

				resourceMap.put("finish_tFileOutputDelimited_9", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Couleur");
				}

				ok_Hash.put("tFileOutputDelimited_9", true);
				end_Hash.put("tFileOutputDelimited_9", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_9 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_1:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk1", 0, "ok");
			}

			tFileInputDelimited_2Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_1 finally ] start
				 */

				currentComponent = "tFileInputDelimited_1";

				/**
				 * [tFileInputDelimited_1 finally ] stop
				 */

				/**
				 * [tLogRow_1 finally ] start
				 */

				currentComponent = "tLogRow_1";

				/**
				 * [tLogRow_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_1 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_1";

				if (resourceMap.get("finish_tFileOutputDelimited_1") == null) {

					java.io.Writer outtFileOutputDelimited_1 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_1");
					if (outtFileOutputDelimited_1 != null) {
						outtFileOutputDelimited_1.flush();
						outtFileOutputDelimited_1.close();
					}

				}

				/**
				 * [tFileOutputDelimited_1 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_2 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_2";

				if (resourceMap.get("finish_tFileOutputDelimited_2") == null) {

					java.io.Writer outtFileOutputDelimited_2 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_2");
					if (outtFileOutputDelimited_2 != null) {
						outtFileOutputDelimited_2.flush();
						outtFileOutputDelimited_2.close();
					}

				}

				/**
				 * [tFileOutputDelimited_2 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_3 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_3";

				if (resourceMap.get("finish_tFileOutputDelimited_3") == null) {

					java.io.Writer outtFileOutputDelimited_3 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_3");
					if (outtFileOutputDelimited_3 != null) {
						outtFileOutputDelimited_3.flush();
						outtFileOutputDelimited_3.close();
					}

				}

				/**
				 * [tFileOutputDelimited_3 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_4 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_4";

				if (resourceMap.get("finish_tFileOutputDelimited_4") == null) {

					java.io.Writer outtFileOutputDelimited_4 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_4");
					if (outtFileOutputDelimited_4 != null) {
						outtFileOutputDelimited_4.flush();
						outtFileOutputDelimited_4.close();
					}

				}

				/**
				 * [tFileOutputDelimited_4 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_5 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_5";

				if (resourceMap.get("finish_tFileOutputDelimited_5") == null) {

					java.io.Writer outtFileOutputDelimited_5 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_5");
					if (outtFileOutputDelimited_5 != null) {
						outtFileOutputDelimited_5.flush();
						outtFileOutputDelimited_5.close();
					}

				}

				/**
				 * [tFileOutputDelimited_5 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_6 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_6";

				if (resourceMap.get("finish_tFileOutputDelimited_6") == null) {

					java.io.Writer outtFileOutputDelimited_6 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_6");
					if (outtFileOutputDelimited_6 != null) {
						outtFileOutputDelimited_6.flush();
						outtFileOutputDelimited_6.close();
					}

				}

				/**
				 * [tFileOutputDelimited_6 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_8 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_8";

				if (resourceMap.get("finish_tFileOutputDelimited_8") == null) {

					java.io.Writer outtFileOutputDelimited_8 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_8");
					if (outtFileOutputDelimited_8 != null) {
						outtFileOutputDelimited_8.flush();
						outtFileOutputDelimited_8.close();
					}

				}

				/**
				 * [tFileOutputDelimited_8 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_9 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_9";

				if (resourceMap.get("finish_tFileOutputDelimited_9") == null) {

					java.io.Writer outtFileOutputDelimited_9 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_9");
					if (outtFileOutputDelimited_9 != null) {
						outtFileOutputDelimited_9.flush();
						outtFileOutputDelimited_9.close();
					}

				}

				/**
				 * [tFileOutputDelimited_9 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", 1);
	}

	public static class ProduitsFamStruct implements routines.system.IPersistableRow<ProduitsFamStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDPrd;

		public String getIDPrd() {
			return this.IDPrd;
		}

		public String nomPrd;

		public String getNomPrd() {
			return this.nomPrd;
		}

		public String paysPrd;

		public String getPaysPrd() {
			return this.paysPrd;
		}

		public Integer prixPrd;

		public Integer getPrixPrd() {
			return this.prixPrd;
		}

		public String Famille;

		public String getFamille() {
			return this.Famille;
		}

		public String Nom;

		public String getNom() {
			return this.Nom;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readInteger(dis);

					this.Famille = readString(dis);

					this.Nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readInteger(dis);

					this.Famille = readString(dis);

					this.Nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// Integer

				writeInteger(this.prixPrd, dos);

				// String

				writeString(this.Famille, dos);

				// String

				writeString(this.Nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// Integer

				writeInteger(this.prixPrd, dos);

				// String

				writeString(this.Famille, dos);

				// String

				writeString(this.Nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDPrd=" + IDPrd);
			sb.append(",nomPrd=" + nomPrd);
			sb.append(",paysPrd=" + paysPrd);
			sb.append(",prixPrd=" + String.valueOf(prixPrd));
			sb.append(",Famille=" + Famille);
			sb.append(",Nom=" + Nom);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(ProduitsFamStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row3Struct implements routines.system.IPersistableRow<row3Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDPrd;

		public String getIDPrd() {
			return this.IDPrd;
		}

		public String nomPrd;

		public String getNomPrd() {
			return this.nomPrd;
		}

		public String paysPrd;

		public String getPaysPrd() {
			return this.paysPrd;
		}

		public Integer prixPrd;

		public Integer getPrixPrd() {
			return this.prixPrd;
		}

		public String IDFam;

		public String getIDFam() {
			return this.IDFam;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readInteger(dis);

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readInteger(dis);

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// Integer

				writeInteger(this.prixPrd, dos);

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// Integer

				writeInteger(this.prixPrd, dos);

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDPrd=" + IDPrd);
			sb.append(",nomPrd=" + nomPrd);
			sb.append(",paysPrd=" + paysPrd);
			sb.append(",prixPrd=" + String.valueOf(prixPrd));
			sb.append(",IDFam=" + IDFam);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row3Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_2Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_2Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public String IDPrd;

		public String getIDPrd() {
			return this.IDPrd;
		}

		public String nomPrd;

		public String getNomPrd() {
			return this.nomPrd;
		}

		public String paysPrd;

		public String getPaysPrd() {
			return this.paysPrd;
		}

		public Integer prixPrd;

		public Integer getPrixPrd() {
			return this.prixPrd;
		}

		public String IDFam;

		public String getIDFam() {
			return this.IDFam;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readInteger(dis);

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readString(dis);

					this.nomPrd = readString(dis);

					this.paysPrd = readString(dis);

					this.prixPrd = readInteger(dis);

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// Integer

				writeInteger(this.prixPrd, dos);

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDPrd, dos);

				// String

				writeString(this.nomPrd, dos);

				// String

				writeString(this.paysPrd, dos);

				// Integer

				writeInteger(this.prixPrd, dos);

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDPrd=" + IDPrd);
			sb.append(",nomPrd=" + nomPrd);
			sb.append(",paysPrd=" + paysPrd);
			sb.append(",prixPrd=" + String.valueOf(prixPrd));
			sb.append(",IDFam=" + IDFam);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_2Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_3Process(globalMap);
				tFileInputDelimited_4Process(globalMap);
				tFileInputDelimited_5Process(globalMap);

				row3Struct row3 = new row3Struct();
				ProduitsFamStruct ProduitsFam = new ProduitsFamStruct();

				/**
				 * [tFileOutputDelimited_7 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_7", false);
				start_Hash.put("tFileOutputDelimited_7", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_7";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "ProduitsFam");
				}

				int tos_count_tFileOutputDelimited_7 = 0;

				String fileName_tFileOutputDelimited_7 = "";
				fileName_tFileOutputDelimited_7 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/ProduitsFam.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_7 = null;
				String extension_tFileOutputDelimited_7 = null;
				String directory_tFileOutputDelimited_7 = null;
				if ((fileName_tFileOutputDelimited_7.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_7.lastIndexOf(".") < fileName_tFileOutputDelimited_7
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7;
						extension_tFileOutputDelimited_7 = "";
					} else {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7.substring(0,
								fileName_tFileOutputDelimited_7.lastIndexOf("."));
						extension_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7
								.substring(fileName_tFileOutputDelimited_7.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7.substring(0,
							fileName_tFileOutputDelimited_7.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_7.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7.substring(0,
								fileName_tFileOutputDelimited_7.lastIndexOf("."));
						extension_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7
								.substring(fileName_tFileOutputDelimited_7.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_7 = fileName_tFileOutputDelimited_7;
						extension_tFileOutputDelimited_7 = "";
					}
					directory_tFileOutputDelimited_7 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_7 = true;
				java.io.File filetFileOutputDelimited_7 = new java.io.File(fileName_tFileOutputDelimited_7);
				globalMap.put("tFileOutputDelimited_7_FILE_NAME", fileName_tFileOutputDelimited_7);
				int nb_line_tFileOutputDelimited_7 = 0;
				int splitedFileNo_tFileOutputDelimited_7 = 0;
				int currentRow_tFileOutputDelimited_7 = 0;

				final String OUT_DELIM_tFileOutputDelimited_7 = /** Start field tFileOutputDelimited_7:FIELDSEPARATOR */
						";"/** End field tFileOutputDelimited_7:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_7 = /**
																		 * Start field
																		 * tFileOutputDelimited_7:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_7:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_7 != null && directory_tFileOutputDelimited_7.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_7 = new java.io.File(directory_tFileOutputDelimited_7);
					if (!dir_tFileOutputDelimited_7.exists()) {
						dir_tFileOutputDelimited_7.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_7 = null;

				java.io.File fileToDelete_tFileOutputDelimited_7 = new java.io.File(fileName_tFileOutputDelimited_7);
				if (fileToDelete_tFileOutputDelimited_7.exists()) {
					fileToDelete_tFileOutputDelimited_7.delete();
				}
				outtFileOutputDelimited_7 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_7, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_7.length() == 0) {
					outtFileOutputDelimited_7.write("IDPrd");
					outtFileOutputDelimited_7.write(OUT_DELIM_tFileOutputDelimited_7);
					outtFileOutputDelimited_7.write("nomPrd");
					outtFileOutputDelimited_7.write(OUT_DELIM_tFileOutputDelimited_7);
					outtFileOutputDelimited_7.write("paysPrd");
					outtFileOutputDelimited_7.write(OUT_DELIM_tFileOutputDelimited_7);
					outtFileOutputDelimited_7.write("prixPrd");
					outtFileOutputDelimited_7.write(OUT_DELIM_tFileOutputDelimited_7);
					outtFileOutputDelimited_7.write("Famille");
					outtFileOutputDelimited_7.write(OUT_DELIM_tFileOutputDelimited_7);
					outtFileOutputDelimited_7.write("Nom");
					outtFileOutputDelimited_7.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_7);
					outtFileOutputDelimited_7.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_7", outtFileOutputDelimited_7);
				resourceMap.put("nb_line_tFileOutputDelimited_7", nb_line_tFileOutputDelimited_7);

				/**
				 * [tFileOutputDelimited_7 begin ] stop
				 */

				/**
				 * [tMap_2 begin ] start
				 */

				ok_Hash.put("tMap_2", false);
				start_Hash.put("tMap_2", System.currentTimeMillis());

				currentComponent = "tMap_2";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row3");
				}

				int tos_count_tMap_2 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct>) globalMap
						.get("tHash_Lookup_row4"));

				row4Struct row4HashKey = new row4Struct();
				row4Struct row4Default = new row4Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct>) globalMap
						.get("tHash_Lookup_row5"));

				tHash_Lookup_row5.initGet();

				row5Struct row5HashKey = new row5Struct();
				row5Struct row5Default = new row5Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct>) globalMap
						.get("tHash_Lookup_row6"));

				row6Struct row6HashKey = new row6Struct();
				row6Struct row6Default = new row6Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_2__Struct {
					String Nom;
				}
				Var__tMap_2__Struct Var__tMap_2 = new Var__tMap_2__Struct();
// ###############################

// ###############################
// # Outputs initialization
				ProduitsFamStruct ProduitsFam_tmp = new ProduitsFamStruct();
// ###############################

				/**
				 * [tMap_2 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_2 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_2", false);
				start_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_2";

				int tos_count_tFileInputDelimited_2 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_2 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_2 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_2 = null;
				int limit_tFileInputDelimited_2 = -1;
				try {

					Object filename_tFileInputDelimited_2 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Produits.csv";
					if (filename_tFileInputDelimited_2 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_2 = 0, random_value_tFileInputDelimited_2 = -1;
						if (footer_value_tFileInputDelimited_2 > 0 || random_value_tFileInputDelimited_2 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_2 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Produits.csv",
								"UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_2, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_2 != null && fid_tFileInputDelimited_2.nextRecord()) {
						rowstate_tFileInputDelimited_2.reset();

						row3 = null;

						boolean whetherReject_tFileInputDelimited_2 = false;
						row3 = new row3Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_2 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_2 = 0;

							row3.IDPrd = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 1;

							row3.nomPrd = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 2;

							row3.paysPrd = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							columnIndexWithD_tFileInputDelimited_2 = 3;

							temp = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);
							if (temp.length() > 0) {

								try {

									row3.prixPrd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_2) {
									globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE",
											ex_tFileInputDelimited_2.getMessage());
									rowstate_tFileInputDelimited_2.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"prixPrd", "row3", temp, ex_tFileInputDelimited_2),
											ex_tFileInputDelimited_2));
								}

							} else {

								row3.prixPrd = null;

							}

							columnIndexWithD_tFileInputDelimited_2 = 4;

							row3.IDFam = fid_tFileInputDelimited_2.get(columnIndexWithD_tFileInputDelimited_2);

							if (rowstate_tFileInputDelimited_2.getException() != null) {
								throw rowstate_tFileInputDelimited_2.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_2_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_2 = true;

							System.err.println(e.getMessage());
							row3 = null;

						}

						/**
						 * [tFileInputDelimited_2 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_2 main ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						tos_count_tFileInputDelimited_2++;

						/**
						 * [tFileInputDelimited_2 main ] stop
						 */

						/**
						 * [tFileInputDelimited_2 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_begin ] stop
						 */
// Start of branch "row3"
						if (row3 != null) {

							/**
							 * [tMap_2 main ] start
							 */

							currentComponent = "tMap_2";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row3"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_2 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_2 = false;
							boolean mainRowRejected_tMap_2 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row4"
							///////////////////////////////////////////////

							boolean forceLooprow4 = false;

							row4Struct row4ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_2 = false;

								row4HashKey.IDFam = row3.IDFam;

								row4HashKey.hashCodeDirty = true;

								tHash_Lookup_row4.lookup(row4HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row4 != null && tHash_Lookup_row4.getCount(row4HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row4'
								// and it contains more one result from keys : row4.IDFam = '" +
								// row4HashKey.IDFam + "'");
							} // G 071

							row4Struct row4 = null;

							row4Struct fromLookup_row4 = null;
							row4 = row4Default;

							if (tHash_Lookup_row4 != null && tHash_Lookup_row4.hasNext()) { // G 099

								fromLookup_row4 = tHash_Lookup_row4.next();

							} // G 099

							if (fromLookup_row4 != null) {
								row4 = fromLookup_row4;
							}

							///////////////////////////////////////////////
							// Starting Lookup Table "row5"
							///////////////////////////////////////////////

							boolean forceLooprow5 = false;

							row5Struct row5ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

								tHash_Lookup_row5.lookup(row5HashKey);

								if (!tHash_Lookup_row5.hasNext()) { // G_TM_M_090

									forceLooprow5 = true;

								} // G_TM_M_090

							} // G_TM_M_020

							else { // G 20 - G 21
								forceLooprow5 = true;
							} // G 21

							row5Struct row5 = null;

							while ((tHash_Lookup_row5 != null && tHash_Lookup_row5.hasNext()) || forceLooprow5) { // G_TM_M_043

								// CALL close loop of lookup 'row5'

								row5Struct fromLookup_row5 = null;
								row5 = row5Default;

								if (!forceLooprow5) { // G 46

									fromLookup_row5 = tHash_Lookup_row5.next();

									if (fromLookup_row5 != null) {
										row5 = fromLookup_row5;
									}

								} // G 46

								forceLooprow5 = false;

								///////////////////////////////////////////////
								// Starting Lookup Table "row6"
								///////////////////////////////////////////////

								boolean forceLooprow6 = false;

								row6Struct row6ObjectFromLookup = null;

								if (!rejectedInnerJoin_tMap_2) { // G_TM_M_020

									hasCasePrimitiveKeyWithNull_tMap_2 = false;

									row6HashKey.IDPrd = (((row3.IDPrd) == null) ? null
											: (routines.system.TypeConvert.String2Integer(row3.IDPrd)));

									row6HashKey.hashCodeDirty = true;

									tHash_Lookup_row6.lookup(row6HashKey);

									if (!tHash_Lookup_row6.hasNext()) { // G_TM_M_090

										rejectedInnerJoin_tMap_2 = true;

									} // G_TM_M_090

								} // G_TM_M_020

								if (tHash_Lookup_row6 != null && tHash_Lookup_row6.getCount(row6HashKey) > 1) { // G 071

									// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row6'
									// and it contains more one result from keys : row6.IDPrd = '" +
									// row6HashKey.IDPrd + "'");
								} // G 071

								row6Struct row6 = null;

								row6Struct fromLookup_row6 = null;
								row6 = row6Default;

								if (tHash_Lookup_row6 != null && tHash_Lookup_row6.hasNext()) { // G 099

									fromLookup_row6 = tHash_Lookup_row6.next();

								} // G 099

								if (fromLookup_row6 != null) {
									row6 = fromLookup_row6;
								}

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_2__Struct Var = Var__tMap_2;
									Var.Nom = row3.prixPrd > row5.Min && row3.prixPrd < row5.Max ? row5.Nom : "";// ###############################
									// ###############################
									// # Output tables

									ProduitsFam = null;

									if (!rejectedInnerJoin_tMap_2) {

// # Output table : 'ProduitsFam'
// # Filter conditions 
										if (

										Var.Nom != ""

										) {
											ProduitsFam_tmp.IDPrd = row3.IDPrd;
											ProduitsFam_tmp.nomPrd = row3.nomPrd;
											ProduitsFam_tmp.paysPrd = row3.paysPrd;
											ProduitsFam_tmp.prixPrd = row3.prixPrd;
											ProduitsFam_tmp.Famille = row4.Famille;
											ProduitsFam_tmp.Nom = Var.Nom;
											ProduitsFam = ProduitsFam_tmp;
										} // closing filter/reject
									} // closing inner join bracket (2)
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_2 = false;

								tos_count_tMap_2++;

								/**
								 * [tMap_2 main ] stop
								 */

								/**
								 * [tMap_2 process_data_begin ] start
								 */

								currentComponent = "tMap_2";

								/**
								 * [tMap_2 process_data_begin ] stop
								 */
// Start of branch "ProduitsFam"
								if (ProduitsFam != null) {

									/**
									 * [tFileOutputDelimited_7 main ] start
									 */

									currentComponent = "tFileOutputDelimited_7";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "ProduitsFam"

										);
									}

									StringBuilder sb_tFileOutputDelimited_7 = new StringBuilder();
									if (ProduitsFam.IDPrd != null) {
										sb_tFileOutputDelimited_7.append(ProduitsFam.IDPrd);
									}
									sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
									if (ProduitsFam.nomPrd != null) {
										sb_tFileOutputDelimited_7.append(ProduitsFam.nomPrd);
									}
									sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
									if (ProduitsFam.paysPrd != null) {
										sb_tFileOutputDelimited_7.append(ProduitsFam.paysPrd);
									}
									sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
									if (ProduitsFam.prixPrd != null) {
										sb_tFileOutputDelimited_7.append(ProduitsFam.prixPrd);
									}
									sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
									if (ProduitsFam.Famille != null) {
										sb_tFileOutputDelimited_7.append(ProduitsFam.Famille);
									}
									sb_tFileOutputDelimited_7.append(OUT_DELIM_tFileOutputDelimited_7);
									if (ProduitsFam.Nom != null) {
										sb_tFileOutputDelimited_7.append(ProduitsFam.Nom);
									}
									sb_tFileOutputDelimited_7.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_7);

									nb_line_tFileOutputDelimited_7++;
									resourceMap.put("nb_line_tFileOutputDelimited_7", nb_line_tFileOutputDelimited_7);

									outtFileOutputDelimited_7.write(sb_tFileOutputDelimited_7.toString());

									tos_count_tFileOutputDelimited_7++;

									/**
									 * [tFileOutputDelimited_7 main ] stop
									 */

									/**
									 * [tFileOutputDelimited_7 process_data_begin ] start
									 */

									currentComponent = "tFileOutputDelimited_7";

									/**
									 * [tFileOutputDelimited_7 process_data_begin ] stop
									 */

									/**
									 * [tFileOutputDelimited_7 process_data_end ] start
									 */

									currentComponent = "tFileOutputDelimited_7";

									/**
									 * [tFileOutputDelimited_7 process_data_end ] stop
									 */

								} // End of branch "ProduitsFam"

							} // close loop of lookup 'row5' // G_TM_M_043

							/**
							 * [tMap_2 process_data_end ] start
							 */

							currentComponent = "tMap_2";

							/**
							 * [tMap_2 process_data_end ] stop
							 */

						} // End of branch "row3"

						/**
						 * [tFileInputDelimited_2 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

						/**
						 * [tFileInputDelimited_2 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_2 end ] start
						 */

						currentComponent = "tFileInputDelimited_2";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Produits.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_2 != null) {
							fid_tFileInputDelimited_2.close();
						}
					}
					if (fid_tFileInputDelimited_2 != null) {
						globalMap.put("tFileInputDelimited_2_NB_LINE", fid_tFileInputDelimited_2.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_2", true);
				end_Hash.put("tFileInputDelimited_2", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_2 end ] stop
				 */

				/**
				 * [tMap_2 end ] start
				 */

				currentComponent = "tMap_2";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row4 != null) {
					tHash_Lookup_row4.endGet();
				}
				globalMap.remove("tHash_Lookup_row4");

				if (tHash_Lookup_row5 != null) {
					tHash_Lookup_row5.endGet();
				}
				globalMap.remove("tHash_Lookup_row5");

				if (tHash_Lookup_row6 != null) {
					tHash_Lookup_row6.endGet();
				}
				globalMap.remove("tHash_Lookup_row6");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row3");
				}

				ok_Hash.put("tMap_2", true);
				end_Hash.put("tMap_2", System.currentTimeMillis());

				/**
				 * [tMap_2 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_7 end ] start
				 */

				currentComponent = "tFileOutputDelimited_7";

				if (outtFileOutputDelimited_7 != null) {
					outtFileOutputDelimited_7.flush();
					outtFileOutputDelimited_7.close();
				}

				globalMap.put("tFileOutputDelimited_7_NB_LINE", nb_line_tFileOutputDelimited_7);
				globalMap.put("tFileOutputDelimited_7_FILE_NAME", fileName_tFileOutputDelimited_7);

				resourceMap.put("finish_tFileOutputDelimited_7", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "ProduitsFam");
				}

				ok_Hash.put("tFileOutputDelimited_7", true);
				end_Hash.put("tFileOutputDelimited_7", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_7 end ] stop
				 */

			} // end the resume

			if (resumeEntryMethodName == null || globalResumeTicket) {
				resumeUtil.addLog("CHECKPOINT", "CONNECTION:SUBJOB_OK:tFileInputDelimited_2:OnSubjobOk", "",
						Thread.currentThread().getId() + "", "", "", "", "", "");
			}

			if (execStat) {
				runStat.updateStatOnConnection("OnSubjobOk2", 0, "ok");
			}

			tFileInputDelimited_6Process(globalMap);

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row4");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row5");

			// free memory for "tMap_2"
			globalMap.remove("tHash_Lookup_row6");

			try {

				/**
				 * [tFileInputDelimited_2 finally ] start
				 */

				currentComponent = "tFileInputDelimited_2";

				/**
				 * [tFileInputDelimited_2 finally ] stop
				 */

				/**
				 * [tMap_2 finally ] start
				 */

				currentComponent = "tMap_2";

				/**
				 * [tMap_2 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_7 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_7";

				if (resourceMap.get("finish_tFileOutputDelimited_7") == null) {

					java.io.Writer outtFileOutputDelimited_7 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_7");
					if (outtFileOutputDelimited_7 != null) {
						outtFileOutputDelimited_7.flush();
						outtFileOutputDelimited_7.close();
					}

				}

				/**
				 * [tFileOutputDelimited_7 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_2_SUBPROCESS_STATE", 1);
	}

	public static class Commande_LIG_CoulStruct implements routines.system.IPersistableRow<Commande_LIG_CoulStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDPrd;

		public Integer getIDPrd() {
			return this.IDPrd;
		}

		public Integer qtt;

		public Integer getQtt() {
			return this.qtt;
		}

		public Integer prixVenteInitial;

		public Integer getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public String paysClt;

		public String getPaysClt() {
			return this.paysClt;
		}

		public Integer annee;

		public Integer getAnnee() {
			return this.annee;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					this.paysClt = readString(dis);

					this.annee = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					this.paysClt = readString(dis);

					this.annee = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.paysClt, dos);

				// Integer

				writeInteger(this.annee, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.paysClt, dos);

				// Integer

				writeInteger(this.annee, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDPrd=" + String.valueOf(IDPrd));
			sb.append(",qtt=" + String.valueOf(qtt));
			sb.append(",prixVenteInitial=" + String.valueOf(prixVenteInitial));
			sb.append(",Couleur=" + Couleur);
			sb.append(",paysClt=" + paysClt);
			sb.append(",annee=" + String.valueOf(annee));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Commande_LIG_CoulStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row7Struct implements routines.system.IPersistableRow<row7Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDPrd;

		public Integer getIDPrd() {
			return this.IDPrd;
		}

		public Integer qtt;

		public Integer getQtt() {
			return this.qtt;
		}

		public Integer IDCoul;

		public Integer getIDCoul() {
			return this.IDCoul;
		}

		public Integer prixVenteInitial;

		public Integer getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.IDCoul = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.IDCoul = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.IDCoul, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.IDCoul, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDPrd=" + String.valueOf(IDPrd));
			sb.append(",qtt=" + String.valueOf(qtt));
			sb.append(",IDCoul=" + String.valueOf(IDCoul));
			sb.append(",prixVenteInitial=" + String.valueOf(prixVenteInitial));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row7Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_6Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_6Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDPrd;

		public Integer getIDPrd() {
			return this.IDPrd;
		}

		public Integer qtt;

		public Integer getQtt() {
			return this.qtt;
		}

		public Integer IDCoul;

		public Integer getIDCoul() {
			return this.IDCoul;
		}

		public Integer prixVenteInitial;

		public Integer getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.IDCoul = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.IDCoul = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.IDCoul, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.IDCoul, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDPrd=" + String.valueOf(IDPrd));
			sb.append(",qtt=" + String.valueOf(qtt));
			sb.append(",IDCoul=" + String.valueOf(IDCoul));
			sb.append(",prixVenteInitial=" + String.valueOf(prixVenteInitial));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_6Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_6Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_6_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_7Process(globalMap);
				tFileInputDelimited_8Process(globalMap);
				tFileInputDelimited_9Process(globalMap);

				row7Struct row7 = new row7Struct();
				Commande_LIG_CoulStruct Commande_LIG_Coul = new Commande_LIG_CoulStruct();

				/**
				 * [tFileOutputDelimited_10 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_10", false);
				start_Hash.put("tFileOutputDelimited_10", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_10";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Commande_LIG_Coul");
				}

				int tos_count_tFileOutputDelimited_10 = 0;

				String fileName_tFileOutputDelimited_10 = "";
				fileName_tFileOutputDelimited_10 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG_Coul.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_10 = null;
				String extension_tFileOutputDelimited_10 = null;
				String directory_tFileOutputDelimited_10 = null;
				if ((fileName_tFileOutputDelimited_10.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_10.lastIndexOf(".") < fileName_tFileOutputDelimited_10
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_10 = fileName_tFileOutputDelimited_10;
						extension_tFileOutputDelimited_10 = "";
					} else {
						fullName_tFileOutputDelimited_10 = fileName_tFileOutputDelimited_10.substring(0,
								fileName_tFileOutputDelimited_10.lastIndexOf("."));
						extension_tFileOutputDelimited_10 = fileName_tFileOutputDelimited_10
								.substring(fileName_tFileOutputDelimited_10.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_10 = fileName_tFileOutputDelimited_10.substring(0,
							fileName_tFileOutputDelimited_10.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_10.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_10 = fileName_tFileOutputDelimited_10.substring(0,
								fileName_tFileOutputDelimited_10.lastIndexOf("."));
						extension_tFileOutputDelimited_10 = fileName_tFileOutputDelimited_10
								.substring(fileName_tFileOutputDelimited_10.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_10 = fileName_tFileOutputDelimited_10;
						extension_tFileOutputDelimited_10 = "";
					}
					directory_tFileOutputDelimited_10 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_10 = true;
				java.io.File filetFileOutputDelimited_10 = new java.io.File(fileName_tFileOutputDelimited_10);
				globalMap.put("tFileOutputDelimited_10_FILE_NAME", fileName_tFileOutputDelimited_10);
				int nb_line_tFileOutputDelimited_10 = 0;
				int splitedFileNo_tFileOutputDelimited_10 = 0;
				int currentRow_tFileOutputDelimited_10 = 0;

				final String OUT_DELIM_tFileOutputDelimited_10 = /**
																	 * Start field
																	 * tFileOutputDelimited_10:FIELDSEPARATOR
																	 */
						";"/** End field tFileOutputDelimited_10:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_10 = /**
																		 * Start field
																		 * tFileOutputDelimited_10:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_10:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_10 != null
						&& directory_tFileOutputDelimited_10.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_10 = new java.io.File(directory_tFileOutputDelimited_10);
					if (!dir_tFileOutputDelimited_10.exists()) {
						dir_tFileOutputDelimited_10.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_10 = null;

				java.io.File fileToDelete_tFileOutputDelimited_10 = new java.io.File(fileName_tFileOutputDelimited_10);
				if (fileToDelete_tFileOutputDelimited_10.exists()) {
					fileToDelete_tFileOutputDelimited_10.delete();
				}
				outtFileOutputDelimited_10 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_10, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_10.length() == 0) {
					outtFileOutputDelimited_10.write("IDCmd");
					outtFileOutputDelimited_10.write(OUT_DELIM_tFileOutputDelimited_10);
					outtFileOutputDelimited_10.write("IDPrd");
					outtFileOutputDelimited_10.write(OUT_DELIM_tFileOutputDelimited_10);
					outtFileOutputDelimited_10.write("qtt");
					outtFileOutputDelimited_10.write(OUT_DELIM_tFileOutputDelimited_10);
					outtFileOutputDelimited_10.write("prixVenteInitial");
					outtFileOutputDelimited_10.write(OUT_DELIM_tFileOutputDelimited_10);
					outtFileOutputDelimited_10.write("Couleur");
					outtFileOutputDelimited_10.write(OUT_DELIM_tFileOutputDelimited_10);
					outtFileOutputDelimited_10.write("paysClt");
					outtFileOutputDelimited_10.write(OUT_DELIM_tFileOutputDelimited_10);
					outtFileOutputDelimited_10.write("annee");
					outtFileOutputDelimited_10.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_10);
					outtFileOutputDelimited_10.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_10", outtFileOutputDelimited_10);
				resourceMap.put("nb_line_tFileOutputDelimited_10", nb_line_tFileOutputDelimited_10);

				/**
				 * [tFileOutputDelimited_10 begin ] stop
				 */

				/**
				 * [tMap_3 begin ] start
				 */

				ok_Hash.put("tMap_3", false);
				start_Hash.put("tMap_3", System.currentTimeMillis());

				currentComponent = "tMap_3";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row7");
				}

				int tos_count_tMap_3 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct>) globalMap
						.get("tHash_Lookup_row8"));

				row8Struct row8HashKey = new row8Struct();
				row8Struct row8Default = new row8Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct>) globalMap
						.get("tHash_Lookup_row9"));

				row9Struct row9HashKey = new row9Struct();
				row9Struct row9Default = new row9Struct();

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct> tHash_Lookup_row10 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct>) globalMap
						.get("tHash_Lookup_row10"));

				row10Struct row10HashKey = new row10Struct();
				row10Struct row10Default = new row10Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_3__Struct {
				}
				Var__tMap_3__Struct Var__tMap_3 = new Var__tMap_3__Struct();
// ###############################

// ###############################
// # Outputs initialization
				Commande_LIG_CoulStruct Commande_LIG_Coul_tmp = new Commande_LIG_CoulStruct();
// ###############################

				/**
				 * [tMap_3 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_6 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_6", false);
				start_Hash.put("tFileInputDelimited_6", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_6";

				int tos_count_tFileInputDelimited_6 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_6 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_6 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_6 = null;
				int limit_tFileInputDelimited_6 = -1;
				try {

					Object filename_tFileInputDelimited_6 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG.csv";
					if (filename_tFileInputDelimited_6 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_6 = 0, random_value_tFileInputDelimited_6 = -1;
						if (footer_value_tFileInputDelimited_6 > 0 || random_value_tFileInputDelimited_6 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_6 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG.csv",
								"US-ASCII", ";", "\n", false, 1, 0, limit_tFileInputDelimited_6, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_6 != null && fid_tFileInputDelimited_6.nextRecord()) {
						rowstate_tFileInputDelimited_6.reset();

						row7 = null;

						boolean whetherReject_tFileInputDelimited_6 = false;
						row7 = new row7Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_6 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_6 = 0;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row7.IDCmd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDCmd", "row7", temp, ex_tFileInputDelimited_6),
											ex_tFileInputDelimited_6));
								}

							} else {

								row7.IDCmd = null;

							}

							columnIndexWithD_tFileInputDelimited_6 = 1;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row7.IDPrd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDPrd", "row7", temp, ex_tFileInputDelimited_6),
											ex_tFileInputDelimited_6));
								}

							} else {

								row7.IDPrd = null;

							}

							columnIndexWithD_tFileInputDelimited_6 = 2;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row7.qtt = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"qtt", "row7", temp, ex_tFileInputDelimited_6), ex_tFileInputDelimited_6));
								}

							} else {

								row7.qtt = null;

							}

							columnIndexWithD_tFileInputDelimited_6 = 3;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row7.IDCoul = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDCoul", "row7", temp, ex_tFileInputDelimited_6),
											ex_tFileInputDelimited_6));
								}

							} else {

								row7.IDCoul = null;

							}

							columnIndexWithD_tFileInputDelimited_6 = 4;

							temp = fid_tFileInputDelimited_6.get(columnIndexWithD_tFileInputDelimited_6);
							if (temp.length() > 0) {

								try {

									row7.prixVenteInitial = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_6) {
									globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE",
											ex_tFileInputDelimited_6.getMessage());
									rowstate_tFileInputDelimited_6.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"prixVenteInitial", "row7", temp, ex_tFileInputDelimited_6),
											ex_tFileInputDelimited_6));
								}

							} else {

								row7.prixVenteInitial = null;

							}

							if (rowstate_tFileInputDelimited_6.getException() != null) {
								throw rowstate_tFileInputDelimited_6.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_6_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_6 = true;

							System.err.println(e.getMessage());
							row7 = null;

						}

						/**
						 * [tFileInputDelimited_6 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_6 main ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						tos_count_tFileInputDelimited_6++;

						/**
						 * [tFileInputDelimited_6 main ] stop
						 */

						/**
						 * [tFileInputDelimited_6 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						/**
						 * [tFileInputDelimited_6 process_data_begin ] stop
						 */
// Start of branch "row7"
						if (row7 != null) {

							/**
							 * [tMap_3 main ] start
							 */

							currentComponent = "tMap_3";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row7"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_3 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_3 = false;
							boolean mainRowRejected_tMap_3 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row8"
							///////////////////////////////////////////////

							boolean forceLooprow8 = false;

							row8Struct row8ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_3) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_3 = false;

								row8HashKey.IDCoul = row7.IDCoul;

								row8HashKey.hashCodeDirty = true;

								tHash_Lookup_row8.lookup(row8HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row8 != null && tHash_Lookup_row8.getCount(row8HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row8'
								// and it contains more one result from keys : row8.IDCoul = '" +
								// row8HashKey.IDCoul + "'");
							} // G 071

							row8Struct row8 = null;

							row8Struct fromLookup_row8 = null;
							row8 = row8Default;

							if (tHash_Lookup_row8 != null && tHash_Lookup_row8.hasNext()) { // G 099

								fromLookup_row8 = tHash_Lookup_row8.next();

							} // G 099

							if (fromLookup_row8 != null) {
								row8 = fromLookup_row8;
							}

							///////////////////////////////////////////////
							// Starting Lookup Table "row9"
							///////////////////////////////////////////////

							boolean forceLooprow9 = false;

							row9Struct row9ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_3) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_3 = false;

								row9HashKey.IDCmd = row7.IDCmd;

								row9HashKey.hashCodeDirty = true;

								tHash_Lookup_row9.lookup(row9HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row9 != null && tHash_Lookup_row9.getCount(row9HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup 'row9'
								// and it contains more one result from keys : row9.IDCmd = '" +
								// row9HashKey.IDCmd + "'");
							} // G 071

							row9Struct row9 = null;

							row9Struct fromLookup_row9 = null;
							row9 = row9Default;

							if (tHash_Lookup_row9 != null && tHash_Lookup_row9.hasNext()) { // G 099

								fromLookup_row9 = tHash_Lookup_row9.next();

							} // G 099

							if (fromLookup_row9 != null) {
								row9 = fromLookup_row9;
							}

							///////////////////////////////////////////////
							// Starting Lookup Table "row10"
							///////////////////////////////////////////////

							boolean forceLooprow10 = false;

							row10Struct row10ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_3) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_3 = false;

								row10HashKey.IDClt = row9.IDClt;

								row10HashKey.hashCodeDirty = true;

								tHash_Lookup_row10.lookup(row10HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row10 != null && tHash_Lookup_row10.getCount(row10HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
								// 'row10' and it contains more one result from keys : row10.IDClt = '" +
								// row10HashKey.IDClt + "'");
							} // G 071

							row10Struct row10 = null;

							row10Struct fromLookup_row10 = null;
							row10 = row10Default;

							if (tHash_Lookup_row10 != null && tHash_Lookup_row10.hasNext()) { // G 099

								fromLookup_row10 = tHash_Lookup_row10.next();

							} // G 099

							if (fromLookup_row10 != null) {
								row10 = fromLookup_row10;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_3__Struct Var = Var__tMap_3;// ###############################
								// ###############################
								// # Output tables

								Commande_LIG_Coul = null;

// # Output table : 'Commande_LIG_Coul'
								Commande_LIG_Coul_tmp.IDCmd = row7.IDCmd;
								Commande_LIG_Coul_tmp.IDPrd = row7.IDPrd;
								Commande_LIG_Coul_tmp.qtt = row7.qtt;
								Commande_LIG_Coul_tmp.prixVenteInitial = row7.prixVenteInitial;
								Commande_LIG_Coul_tmp.Couleur = row8.Couleur;
								Commande_LIG_Coul_tmp.paysClt = (row10.paysClt.equals("USA") ? "USD"
										: (row10.paysClt.equals("JAPAN") ? "JPY"
												: (row10.paysClt.equals("FRANCE") ? "EUR" : "")));
								Commande_LIG_Coul_tmp.annee = TalendDate.getPartOfDate("YEAR", row9.dateCmd);
								Commande_LIG_Coul = Commande_LIG_Coul_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_3 = false;

							tos_count_tMap_3++;

							/**
							 * [tMap_3 main ] stop
							 */

							/**
							 * [tMap_3 process_data_begin ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_begin ] stop
							 */
// Start of branch "Commande_LIG_Coul"
							if (Commande_LIG_Coul != null) {

								/**
								 * [tFileOutputDelimited_10 main ] start
								 */

								currentComponent = "tFileOutputDelimited_10";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Commande_LIG_Coul"

									);
								}

								StringBuilder sb_tFileOutputDelimited_10 = new StringBuilder();
								if (Commande_LIG_Coul.IDCmd != null) {
									sb_tFileOutputDelimited_10.append(Commande_LIG_Coul.IDCmd);
								}
								sb_tFileOutputDelimited_10.append(OUT_DELIM_tFileOutputDelimited_10);
								if (Commande_LIG_Coul.IDPrd != null) {
									sb_tFileOutputDelimited_10.append(Commande_LIG_Coul.IDPrd);
								}
								sb_tFileOutputDelimited_10.append(OUT_DELIM_tFileOutputDelimited_10);
								if (Commande_LIG_Coul.qtt != null) {
									sb_tFileOutputDelimited_10.append(Commande_LIG_Coul.qtt);
								}
								sb_tFileOutputDelimited_10.append(OUT_DELIM_tFileOutputDelimited_10);
								if (Commande_LIG_Coul.prixVenteInitial != null) {
									sb_tFileOutputDelimited_10.append(Commande_LIG_Coul.prixVenteInitial);
								}
								sb_tFileOutputDelimited_10.append(OUT_DELIM_tFileOutputDelimited_10);
								if (Commande_LIG_Coul.Couleur != null) {
									sb_tFileOutputDelimited_10.append(Commande_LIG_Coul.Couleur);
								}
								sb_tFileOutputDelimited_10.append(OUT_DELIM_tFileOutputDelimited_10);
								if (Commande_LIG_Coul.paysClt != null) {
									sb_tFileOutputDelimited_10.append(Commande_LIG_Coul.paysClt);
								}
								sb_tFileOutputDelimited_10.append(OUT_DELIM_tFileOutputDelimited_10);
								if (Commande_LIG_Coul.annee != null) {
									sb_tFileOutputDelimited_10.append(Commande_LIG_Coul.annee);
								}
								sb_tFileOutputDelimited_10.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_10);

								nb_line_tFileOutputDelimited_10++;
								resourceMap.put("nb_line_tFileOutputDelimited_10", nb_line_tFileOutputDelimited_10);

								outtFileOutputDelimited_10.write(sb_tFileOutputDelimited_10.toString());

								tos_count_tFileOutputDelimited_10++;

								/**
								 * [tFileOutputDelimited_10 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_10 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_10";

								/**
								 * [tFileOutputDelimited_10 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_10 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_10";

								/**
								 * [tFileOutputDelimited_10 process_data_end ] stop
								 */

							} // End of branch "Commande_LIG_Coul"

							/**
							 * [tMap_3 process_data_end ] start
							 */

							currentComponent = "tMap_3";

							/**
							 * [tMap_3 process_data_end ] stop
							 */

						} // End of branch "row7"

						/**
						 * [tFileInputDelimited_6 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_6";

						/**
						 * [tFileInputDelimited_6 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_6 end ] start
						 */

						currentComponent = "tFileInputDelimited_6";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_6 != null) {
							fid_tFileInputDelimited_6.close();
						}
					}
					if (fid_tFileInputDelimited_6 != null) {
						globalMap.put("tFileInputDelimited_6_NB_LINE", fid_tFileInputDelimited_6.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_6", true);
				end_Hash.put("tFileInputDelimited_6", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_6 end ] stop
				 */

				/**
				 * [tMap_3 end ] start
				 */

				currentComponent = "tMap_3";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row8 != null) {
					tHash_Lookup_row8.endGet();
				}
				globalMap.remove("tHash_Lookup_row8");

				if (tHash_Lookup_row9 != null) {
					tHash_Lookup_row9.endGet();
				}
				globalMap.remove("tHash_Lookup_row9");

				if (tHash_Lookup_row10 != null) {
					tHash_Lookup_row10.endGet();
				}
				globalMap.remove("tHash_Lookup_row10");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row7");
				}

				ok_Hash.put("tMap_3", true);
				end_Hash.put("tMap_3", System.currentTimeMillis());

				/**
				 * [tMap_3 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_10 end ] start
				 */

				currentComponent = "tFileOutputDelimited_10";

				if (outtFileOutputDelimited_10 != null) {
					outtFileOutputDelimited_10.flush();
					outtFileOutputDelimited_10.close();
				}

				globalMap.put("tFileOutputDelimited_10_NB_LINE", nb_line_tFileOutputDelimited_10);
				globalMap.put("tFileOutputDelimited_10_FILE_NAME", fileName_tFileOutputDelimited_10);

				resourceMap.put("finish_tFileOutputDelimited_10", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Commande_LIG_Coul");
				}

				ok_Hash.put("tFileOutputDelimited_10", true);
				end_Hash.put("tFileOutputDelimited_10", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_10 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_3"
			globalMap.remove("tHash_Lookup_row8");

			// free memory for "tMap_3"
			globalMap.remove("tHash_Lookup_row9");

			// free memory for "tMap_3"
			globalMap.remove("tHash_Lookup_row10");

			try {

				/**
				 * [tFileInputDelimited_6 finally ] start
				 */

				currentComponent = "tFileInputDelimited_6";

				/**
				 * [tFileInputDelimited_6 finally ] stop
				 */

				/**
				 * [tMap_3 finally ] start
				 */

				currentComponent = "tMap_3";

				/**
				 * [tMap_3 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_10 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_10";

				if (resourceMap.get("finish_tFileOutputDelimited_10") == null) {

					java.io.Writer outtFileOutputDelimited_10 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_10");
					if (outtFileOutputDelimited_10 != null) {
						outtFileOutputDelimited_10.flush();
						outtFileOutputDelimited_10.close();
					}

				}

				/**
				 * [tFileOutputDelimited_10 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_6_SUBPROCESS_STATE", 1);
	}

	public static class row4Struct implements routines.system.IPersistableComparableLookupRow<row4Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String IDFam;

		public String getIDFam() {
			return this.IDFam;
		}

		public String Famille;

		public String getFamille() {
			return this.Famille;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.IDFam == null) ? 0 : this.IDFam.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row4Struct other = (row4Struct) obj;

			if (this.IDFam == null) {
				if (other.IDFam != null)
					return false;

			} else if (!this.IDFam.equals(other.IDFam))

				return false;

			return true;
		}

		public void copyDataTo(row4Struct other) {

			other.IDFam = this.IDFam;
			other.Famille = this.Famille;

		}

		public void copyKeysDataTo(row4Struct other) {

			other.IDFam = this.IDFam;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDFam = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.IDFam, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.Famille = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Famille = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.Famille, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.Famille, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDFam=" + IDFam);
			sb.append(",Famille=" + Famille);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row4Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IDFam, other.IDFam);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_3Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row4Struct row4 = new row4Struct();

				/**
				 * [tAdvancedHash_row4 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row4", false);
				start_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row4");
				}

				int tos_count_tAdvancedHash_row4 = 0;

				// connection name:row4
				// source node:tFileInputDelimited_3 - inputs:(after_tFileInputDelimited_2)
				// outputs:(row4,row4) | target node:tAdvancedHash_row4 - inputs:(row4)
				// outputs:()
				// linked node: tMap_2 - inputs:(row3,row4,row5,row6) outputs:(ProduitsFam)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row4 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row4Struct> tHash_Lookup_row4 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row4Struct>getLookup(matchingModeEnum_row4);

				globalMap.put("tHash_Lookup_row4", tHash_Lookup_row4);

				/**
				 * [tAdvancedHash_row4 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_3 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_3", false);
				start_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_3";

				int tos_count_tFileInputDelimited_3 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_3 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_3 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_3 = null;
				int limit_tFileInputDelimited_3 = -1;
				try {

					Object filename_tFileInputDelimited_3 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Familles.csv";
					if (filename_tFileInputDelimited_3 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_3 = 0, random_value_tFileInputDelimited_3 = -1;
						if (footer_value_tFileInputDelimited_3 > 0 || random_value_tFileInputDelimited_3 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_3 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Familles.csv",
								"US-ASCII", ";", "\n", false, 0, 0, limit_tFileInputDelimited_3, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_3 != null && fid_tFileInputDelimited_3.nextRecord()) {
						rowstate_tFileInputDelimited_3.reset();

						row4 = null;

						row4 = null;

						boolean whetherReject_tFileInputDelimited_3 = false;
						row4 = new row4Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_3 = 0;

							columnIndexWithD_tFileInputDelimited_3 = 0;

							row4.IDFam = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							columnIndexWithD_tFileInputDelimited_3 = 1;

							row4.Famille = fid_tFileInputDelimited_3.get(columnIndexWithD_tFileInputDelimited_3);

							if (rowstate_tFileInputDelimited_3.getException() != null) {
								throw rowstate_tFileInputDelimited_3.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_3_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_3 = true;

							System.err.println(e.getMessage());
							row4 = null;

						}

						/**
						 * [tFileInputDelimited_3 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_3 main ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						tos_count_tFileInputDelimited_3++;

						/**
						 * [tFileInputDelimited_3 main ] stop
						 */

						/**
						 * [tFileInputDelimited_3 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_begin ] stop
						 */
// Start of branch "row4"
						if (row4 != null) {

							/**
							 * [tAdvancedHash_row4 main ] start
							 */

							currentComponent = "tAdvancedHash_row4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row4"

								);
							}

							row4Struct row4_HashRow = new row4Struct();

							row4_HashRow.IDFam = row4.IDFam;

							row4_HashRow.Famille = row4.Famille;

							tHash_Lookup_row4.put(row4_HashRow);

							tos_count_tAdvancedHash_row4++;

							/**
							 * [tAdvancedHash_row4 main ] stop
							 */

							/**
							 * [tAdvancedHash_row4 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row4";

							/**
							 * [tAdvancedHash_row4 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row4 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row4";

							/**
							 * [tAdvancedHash_row4 process_data_end ] stop
							 */

						} // End of branch "row4"

						/**
						 * [tFileInputDelimited_3 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

						/**
						 * [tFileInputDelimited_3 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_3 end ] start
						 */

						currentComponent = "tFileInputDelimited_3";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Familles.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_3 != null) {
							fid_tFileInputDelimited_3.close();
						}
					}
					if (fid_tFileInputDelimited_3 != null) {
						globalMap.put("tFileInputDelimited_3_NB_LINE", fid_tFileInputDelimited_3.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_3", true);
				end_Hash.put("tFileInputDelimited_3", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_3 end ] stop
				 */

				/**
				 * [tAdvancedHash_row4 end ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				tHash_Lookup_row4.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row4");
				}

				ok_Hash.put("tAdvancedHash_row4", true);
				end_Hash.put("tAdvancedHash_row4", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row4 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_3 finally ] start
				 */

				currentComponent = "tFileInputDelimited_3";

				/**
				 * [tFileInputDelimited_3 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row4 finally ] start
				 */

				currentComponent = "tAdvancedHash_row4";

				/**
				 * [tAdvancedHash_row4 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_3_SUBPROCESS_STATE", 1);
	}

	public static class row5Struct implements routines.system.IPersistableRow<row5Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer Min;

		public Integer getMin() {
			return this.Min;
		}

		public Integer Max;

		public Integer getMax() {
			return this.Max;
		}

		public String Nom;

		public String getNom() {
			return this.Nom;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.Min = readInteger(dis);

					this.Max = readInteger(dis);

					this.Nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.Min = readInteger(dis);

					this.Max = readInteger(dis);

					this.Nom = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Min, dos);

				// Integer

				writeInteger(this.Max, dos);

				// String

				writeString(this.Nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.Min, dos);

				// Integer

				writeInteger(this.Max, dos);

				// String

				writeString(this.Nom, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Min=" + String.valueOf(Min));
			sb.append(",Max=" + String.valueOf(Max));
			sb.append(",Nom=" + Nom);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row5Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_4Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row5Struct row5 = new row5Struct();

				/**
				 * [tAdvancedHash_row5 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row5", false);
				start_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row5";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row5");
				}

				int tos_count_tAdvancedHash_row5 = 0;

				// connection name:row5
				// source node:tFileInputDelimited_4 - inputs:(after_tFileInputDelimited_2)
				// outputs:(row5,row5) | target node:tAdvancedHash_row5 - inputs:(row5)
				// outputs:()
				// linked node: tMap_2 - inputs:(row3,row4,row5,row6) outputs:(ProduitsFam)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row5 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.ALL_ROWS;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row5Struct> tHash_Lookup_row5 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row5Struct>getLookup(matchingModeEnum_row5);

				globalMap.put("tHash_Lookup_row5", tHash_Lookup_row5);

				/**
				 * [tAdvancedHash_row5 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_4 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_4", false);
				start_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_4";

				int tos_count_tFileInputDelimited_4 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_4 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_4 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_4 = null;
				int limit_tFileInputDelimited_4 = -1;
				try {

					Object filename_tFileInputDelimited_4 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Gammes.csv";
					if (filename_tFileInputDelimited_4 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_4 = 0, random_value_tFileInputDelimited_4 = -1;
						if (footer_value_tFileInputDelimited_4 > 0 || random_value_tFileInputDelimited_4 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_4 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Gammes.csv",
								"UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_4, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_4 != null && fid_tFileInputDelimited_4.nextRecord()) {
						rowstate_tFileInputDelimited_4.reset();

						row5 = null;

						row5 = null;

						boolean whetherReject_tFileInputDelimited_4 = false;
						row5 = new row5Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_4 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_4 = 0;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row5.Min = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Min", "row5", temp, ex_tFileInputDelimited_4), ex_tFileInputDelimited_4));
								}

							} else {

								row5.Min = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 1;

							temp = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);
							if (temp.length() > 0) {

								try {

									row5.Max = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_4) {
									globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE",
											ex_tFileInputDelimited_4.getMessage());
									rowstate_tFileInputDelimited_4.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Max", "row5", temp, ex_tFileInputDelimited_4), ex_tFileInputDelimited_4));
								}

							} else {

								row5.Max = null;

							}

							columnIndexWithD_tFileInputDelimited_4 = 2;

							row5.Nom = fid_tFileInputDelimited_4.get(columnIndexWithD_tFileInputDelimited_4);

							if (rowstate_tFileInputDelimited_4.getException() != null) {
								throw rowstate_tFileInputDelimited_4.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_4_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_4 = true;

							System.err.println(e.getMessage());
							row5 = null;

						}

						/**
						 * [tFileInputDelimited_4 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_4 main ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						tos_count_tFileInputDelimited_4++;

						/**
						 * [tFileInputDelimited_4 main ] stop
						 */

						/**
						 * [tFileInputDelimited_4 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_begin ] stop
						 */
// Start of branch "row5"
						if (row5 != null) {

							/**
							 * [tAdvancedHash_row5 main ] start
							 */

							currentComponent = "tAdvancedHash_row5";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row5"

								);
							}

							row5Struct row5_HashRow = new row5Struct();

							row5_HashRow.Min = row5.Min;

							row5_HashRow.Max = row5.Max;

							row5_HashRow.Nom = row5.Nom;

							tHash_Lookup_row5.put(row5_HashRow);

							tos_count_tAdvancedHash_row5++;

							/**
							 * [tAdvancedHash_row5 main ] stop
							 */

							/**
							 * [tAdvancedHash_row5 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row5";

							/**
							 * [tAdvancedHash_row5 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row5 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row5";

							/**
							 * [tAdvancedHash_row5 process_data_end ] stop
							 */

						} // End of branch "row5"

						/**
						 * [tFileInputDelimited_4 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

						/**
						 * [tFileInputDelimited_4 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_4 end ] start
						 */

						currentComponent = "tFileInputDelimited_4";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Gammes.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_4 != null) {
							fid_tFileInputDelimited_4.close();
						}
					}
					if (fid_tFileInputDelimited_4 != null) {
						globalMap.put("tFileInputDelimited_4_NB_LINE", fid_tFileInputDelimited_4.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_4", true);
				end_Hash.put("tFileInputDelimited_4", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_4 end ] stop
				 */

				/**
				 * [tAdvancedHash_row5 end ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				tHash_Lookup_row5.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row5");
				}

				ok_Hash.put("tAdvancedHash_row5", true);
				end_Hash.put("tAdvancedHash_row5", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row5 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_4 finally ] start
				 */

				currentComponent = "tFileInputDelimited_4";

				/**
				 * [tFileInputDelimited_4 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row5 finally ] start
				 */

				currentComponent = "tAdvancedHash_row5";

				/**
				 * [tAdvancedHash_row5 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_4_SUBPROCESS_STATE", 1);
	}

	public static class row6Struct implements routines.system.IPersistableComparableLookupRow<row6Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDPrd;

		public Integer getIDPrd() {
			return this.IDPrd;
		}

		public Integer qtt;

		public Integer getQtt() {
			return this.qtt;
		}

		public Integer IDCoul;

		public Integer getIDCoul() {
			return this.IDCoul;
		}

		public Integer prixVenteInitial;

		public Integer getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.IDPrd == null) ? 0 : this.IDPrd.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row6Struct other = (row6Struct) obj;

			if (this.IDPrd == null) {
				if (other.IDPrd != null)
					return false;

			} else if (!this.IDPrd.equals(other.IDPrd))

				return false;

			return true;
		}

		public void copyDataTo(row6Struct other) {

			other.IDCmd = this.IDCmd;
			other.IDPrd = this.IDPrd;
			other.qtt = this.qtt;
			other.IDCoul = this.IDCoul;
			other.prixVenteInitial = this.prixVenteInitial;

		}

		public void copyKeysDataTo(row6Struct other) {

			other.IDPrd = this.IDPrd;

		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDPrd = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDPrd, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDPrd, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.IDCmd = readInteger(dis, ois);

				this.qtt = readInteger(dis, ois);

				this.IDCoul = readInteger(dis, ois);

				this.prixVenteInitial = readInteger(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.IDCmd = readInteger(dis, objectIn);

				this.qtt = readInteger(dis, objectIn);

				this.IDCoul = readInteger(dis, objectIn);

				this.prixVenteInitial = readInteger(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.IDCmd, dos, oos);

				writeInteger(this.qtt, dos, oos);

				writeInteger(this.IDCoul, dos, oos);

				writeInteger(this.prixVenteInitial, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.IDCmd, dos, objectOut);

				writeInteger(this.qtt, dos, objectOut);

				writeInteger(this.IDCoul, dos, objectOut);

				writeInteger(this.prixVenteInitial, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDPrd=" + String.valueOf(IDPrd));
			sb.append(",qtt=" + String.valueOf(qtt));
			sb.append(",IDCoul=" + String.valueOf(IDCoul));
			sb.append(",prixVenteInitial=" + String.valueOf(prixVenteInitial));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row6Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IDPrd, other.IDPrd);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_5Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row6Struct row6 = new row6Struct();

				/**
				 * [tAdvancedHash_row6 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row6", false);
				start_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row6";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row6");
				}

				int tos_count_tAdvancedHash_row6 = 0;

				// connection name:row6
				// source node:tFileInputDelimited_5 - inputs:(after_tFileInputDelimited_2)
				// outputs:(row6,row6) | target node:tAdvancedHash_row6 - inputs:(row6)
				// outputs:()
				// linked node: tMap_2 - inputs:(row3,row4,row5,row6) outputs:(ProduitsFam)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row6 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row6Struct> tHash_Lookup_row6 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row6Struct>getLookup(matchingModeEnum_row6);

				globalMap.put("tHash_Lookup_row6", tHash_Lookup_row6);

				/**
				 * [tAdvancedHash_row6 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_5 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_5", false);
				start_Hash.put("tFileInputDelimited_5", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_5";

				int tos_count_tFileInputDelimited_5 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_5 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_5 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_5 = null;
				int limit_tFileInputDelimited_5 = -1;
				try {

					Object filename_tFileInputDelimited_5 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG.csv";
					if (filename_tFileInputDelimited_5 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_5 = 0, random_value_tFileInputDelimited_5 = -1;
						if (footer_value_tFileInputDelimited_5 > 0 || random_value_tFileInputDelimited_5 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_5 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG.csv",
								"US-ASCII", ";", "\n", false, 1, 0, limit_tFileInputDelimited_5, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_5 != null && fid_tFileInputDelimited_5.nextRecord()) {
						rowstate_tFileInputDelimited_5.reset();

						row6 = null;

						row6 = null;

						boolean whetherReject_tFileInputDelimited_5 = false;
						row6 = new row6Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_5 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_5 = 0;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row6.IDCmd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDCmd", "row6", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row6.IDCmd = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 1;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row6.IDPrd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDPrd", "row6", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row6.IDPrd = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 2;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row6.qtt = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"qtt", "row6", temp, ex_tFileInputDelimited_5), ex_tFileInputDelimited_5));
								}

							} else {

								row6.qtt = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 3;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row6.IDCoul = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDCoul", "row6", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row6.IDCoul = null;

							}

							columnIndexWithD_tFileInputDelimited_5 = 4;

							temp = fid_tFileInputDelimited_5.get(columnIndexWithD_tFileInputDelimited_5);
							if (temp.length() > 0) {

								try {

									row6.prixVenteInitial = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_5) {
									globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE",
											ex_tFileInputDelimited_5.getMessage());
									rowstate_tFileInputDelimited_5.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"prixVenteInitial", "row6", temp, ex_tFileInputDelimited_5),
											ex_tFileInputDelimited_5));
								}

							} else {

								row6.prixVenteInitial = null;

							}

							if (rowstate_tFileInputDelimited_5.getException() != null) {
								throw rowstate_tFileInputDelimited_5.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_5_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_5 = true;

							System.err.println(e.getMessage());
							row6 = null;

						}

						/**
						 * [tFileInputDelimited_5 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_5 main ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						tos_count_tFileInputDelimited_5++;

						/**
						 * [tFileInputDelimited_5 main ] stop
						 */

						/**
						 * [tFileInputDelimited_5 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						/**
						 * [tFileInputDelimited_5 process_data_begin ] stop
						 */
// Start of branch "row6"
						if (row6 != null) {

							/**
							 * [tAdvancedHash_row6 main ] start
							 */

							currentComponent = "tAdvancedHash_row6";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row6"

								);
							}

							row6Struct row6_HashRow = new row6Struct();

							row6_HashRow.IDCmd = row6.IDCmd;

							row6_HashRow.IDPrd = row6.IDPrd;

							row6_HashRow.qtt = row6.qtt;

							row6_HashRow.IDCoul = row6.IDCoul;

							row6_HashRow.prixVenteInitial = row6.prixVenteInitial;

							tHash_Lookup_row6.put(row6_HashRow);

							tos_count_tAdvancedHash_row6++;

							/**
							 * [tAdvancedHash_row6 main ] stop
							 */

							/**
							 * [tAdvancedHash_row6 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row6";

							/**
							 * [tAdvancedHash_row6 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row6 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row6";

							/**
							 * [tAdvancedHash_row6 process_data_end ] stop
							 */

						} // End of branch "row6"

						/**
						 * [tFileInputDelimited_5 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_5";

						/**
						 * [tFileInputDelimited_5 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_5 end ] start
						 */

						currentComponent = "tFileInputDelimited_5";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_5 != null) {
							fid_tFileInputDelimited_5.close();
						}
					}
					if (fid_tFileInputDelimited_5 != null) {
						globalMap.put("tFileInputDelimited_5_NB_LINE", fid_tFileInputDelimited_5.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_5", true);
				end_Hash.put("tFileInputDelimited_5", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_5 end ] stop
				 */

				/**
				 * [tAdvancedHash_row6 end ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				tHash_Lookup_row6.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row6");
				}

				ok_Hash.put("tAdvancedHash_row6", true);
				end_Hash.put("tAdvancedHash_row6", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row6 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_5 finally ] start
				 */

				currentComponent = "tFileInputDelimited_5";

				/**
				 * [tFileInputDelimited_5 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row6 finally ] start
				 */

				currentComponent = "tAdvancedHash_row6";

				/**
				 * [tAdvancedHash_row6 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_5_SUBPROCESS_STATE", 1);
	}

	public static class row8Struct implements routines.system.IPersistableComparableLookupRow<row8Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer IDCoul;

		public Integer getIDCoul() {
			return this.IDCoul;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.IDCoul == null) ? 0 : this.IDCoul.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row8Struct other = (row8Struct) obj;

			if (this.IDCoul == null) {
				if (other.IDCoul != null)
					return false;

			} else if (!this.IDCoul.equals(other.IDCoul))

				return false;

			return true;
		}

		public void copyDataTo(row8Struct other) {

			other.IDCoul = this.IDCoul;
			other.Couleur = this.Couleur;

		}

		public void copyKeysDataTo(row8Struct other) {

			other.IDCoul = this.IDCoul;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCoul = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCoul = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCoul, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCoul, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.Couleur = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.Couleur = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.Couleur, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.Couleur, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCoul=" + String.valueOf(IDCoul));
			sb.append(",Couleur=" + Couleur);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row8Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IDCoul, other.IDCoul);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_7Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_7_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row8Struct row8 = new row8Struct();

				/**
				 * [tAdvancedHash_row8 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row8", false);
				start_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row8";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row8");
				}

				int tos_count_tAdvancedHash_row8 = 0;

				// connection name:row8
				// source node:tFileInputDelimited_7 - inputs:(after_tFileInputDelimited_6)
				// outputs:(row8,row8) | target node:tAdvancedHash_row8 - inputs:(row8)
				// outputs:()
				// linked node: tMap_3 - inputs:(row7,row8,row9,row10)
				// outputs:(Commande_LIG_Coul)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row8 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row8Struct> tHash_Lookup_row8 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row8Struct>getLookup(matchingModeEnum_row8);

				globalMap.put("tHash_Lookup_row8", tHash_Lookup_row8);

				/**
				 * [tAdvancedHash_row8 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_7 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_7", false);
				start_Hash.put("tFileInputDelimited_7", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_7";

				int tos_count_tFileInputDelimited_7 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_7 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_7 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_7 = null;
				int limit_tFileInputDelimited_7 = -1;
				try {

					Object filename_tFileInputDelimited_7 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Couleurs.csv";
					if (filename_tFileInputDelimited_7 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_7 = 0, random_value_tFileInputDelimited_7 = -1;
						if (footer_value_tFileInputDelimited_7 > 0 || random_value_tFileInputDelimited_7 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_7 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Couleurs.csv",
								"UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_7, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_7_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_7 != null && fid_tFileInputDelimited_7.nextRecord()) {
						rowstate_tFileInputDelimited_7.reset();

						row8 = null;

						row8 = null;

						boolean whetherReject_tFileInputDelimited_7 = false;
						row8 = new row8Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_7 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_7 = 0;

							temp = fid_tFileInputDelimited_7.get(columnIndexWithD_tFileInputDelimited_7);
							if (temp.length() > 0) {

								try {

									row8.IDCoul = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_7) {
									globalMap.put("tFileInputDelimited_7_ERROR_MESSAGE",
											ex_tFileInputDelimited_7.getMessage());
									rowstate_tFileInputDelimited_7.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDCoul", "row8", temp, ex_tFileInputDelimited_7),
											ex_tFileInputDelimited_7));
								}

							} else {

								row8.IDCoul = null;

							}

							columnIndexWithD_tFileInputDelimited_7 = 1;

							row8.Couleur = fid_tFileInputDelimited_7.get(columnIndexWithD_tFileInputDelimited_7);

							if (rowstate_tFileInputDelimited_7.getException() != null) {
								throw rowstate_tFileInputDelimited_7.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_7_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_7 = true;

							System.err.println(e.getMessage());
							row8 = null;

						}

						/**
						 * [tFileInputDelimited_7 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_7 main ] start
						 */

						currentComponent = "tFileInputDelimited_7";

						tos_count_tFileInputDelimited_7++;

						/**
						 * [tFileInputDelimited_7 main ] stop
						 */

						/**
						 * [tFileInputDelimited_7 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_7";

						/**
						 * [tFileInputDelimited_7 process_data_begin ] stop
						 */
// Start of branch "row8"
						if (row8 != null) {

							/**
							 * [tAdvancedHash_row8 main ] start
							 */

							currentComponent = "tAdvancedHash_row8";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row8"

								);
							}

							row8Struct row8_HashRow = new row8Struct();

							row8_HashRow.IDCoul = row8.IDCoul;

							row8_HashRow.Couleur = row8.Couleur;

							tHash_Lookup_row8.put(row8_HashRow);

							tos_count_tAdvancedHash_row8++;

							/**
							 * [tAdvancedHash_row8 main ] stop
							 */

							/**
							 * [tAdvancedHash_row8 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row8";

							/**
							 * [tAdvancedHash_row8 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row8 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row8";

							/**
							 * [tAdvancedHash_row8 process_data_end ] stop
							 */

						} // End of branch "row8"

						/**
						 * [tFileInputDelimited_7 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_7";

						/**
						 * [tFileInputDelimited_7 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_7 end ] start
						 */

						currentComponent = "tFileInputDelimited_7";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Couleurs.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_7 != null) {
							fid_tFileInputDelimited_7.close();
						}
					}
					if (fid_tFileInputDelimited_7 != null) {
						globalMap.put("tFileInputDelimited_7_NB_LINE", fid_tFileInputDelimited_7.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_7", true);
				end_Hash.put("tFileInputDelimited_7", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_7 end ] stop
				 */

				/**
				 * [tAdvancedHash_row8 end ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				tHash_Lookup_row8.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row8");
				}

				ok_Hash.put("tAdvancedHash_row8", true);
				end_Hash.put("tAdvancedHash_row8", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row8 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_7 finally ] start
				 */

				currentComponent = "tFileInputDelimited_7";

				/**
				 * [tFileInputDelimited_7 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row8 finally ] start
				 */

				currentComponent = "tAdvancedHash_row8";

				/**
				 * [tAdvancedHash_row8 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_7_SUBPROCESS_STATE", 1);
	}

	public static class row9Struct implements routines.system.IPersistableComparableLookupRow<row9Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDClt;

		public Integer getIDClt() {
			return this.IDClt;
		}

		public Integer IDVend;

		public Integer getIDVend() {
			return this.IDVend;
		}

		public java.util.Date dateCmd;

		public java.util.Date getDateCmd() {
			return this.dateCmd;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.IDCmd == null) ? 0 : this.IDCmd.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row9Struct other = (row9Struct) obj;

			if (this.IDCmd == null) {
				if (other.IDCmd != null)
					return false;

			} else if (!this.IDCmd.equals(other.IDCmd))

				return false;

			return true;
		}

		public void copyDataTo(row9Struct other) {

			other.IDCmd = this.IDCmd;
			other.IDClt = this.IDClt;
			other.IDVend = this.IDVend;
			other.dateCmd = this.dateCmd;

		}

		public void copyKeysDataTo(row9Struct other) {

			other.IDCmd = this.IDCmd;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private Integer readInteger(DataInputStream dis, ObjectInputStream ois) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			Integer intReturn;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = unmarshaller.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private java.util.Date readDate(DataInputStream dis, ObjectInputStream ois) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.IDClt = readInteger(dis, ois);

				this.IDVend = readInteger(dis, ois);

				this.dateCmd = readDate(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.IDClt = readInteger(dis, objectIn);

				this.IDVend = readInteger(dis, objectIn);

				this.dateCmd = readDate(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeInteger(this.IDClt, dos, oos);

				writeInteger(this.IDVend, dos, oos);

				writeDate(this.dateCmd, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeInteger(this.IDClt, dos, objectOut);

				writeInteger(this.IDVend, dos, objectOut);

				writeDate(this.dateCmd, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDClt=" + String.valueOf(IDClt));
			sb.append(",IDVend=" + String.valueOf(IDVend));
			sb.append(",dateCmd=" + String.valueOf(dateCmd));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row9Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IDCmd, other.IDCmd);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_8Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_8_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row9Struct row9 = new row9Struct();

				/**
				 * [tAdvancedHash_row9 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row9", false);
				start_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row9";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row9");
				}

				int tos_count_tAdvancedHash_row9 = 0;

				// connection name:row9
				// source node:tFileInputDelimited_8 - inputs:(after_tFileInputDelimited_6)
				// outputs:(row9,row9) | target node:tAdvancedHash_row9 - inputs:(row9)
				// outputs:()
				// linked node: tMap_3 - inputs:(row7,row8,row9,row10)
				// outputs:(Commande_LIG_Coul)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row9 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row9Struct> tHash_Lookup_row9 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row9Struct>getLookup(matchingModeEnum_row9);

				globalMap.put("tHash_Lookup_row9", tHash_Lookup_row9);

				/**
				 * [tAdvancedHash_row9 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_8 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_8", false);
				start_Hash.put("tFileInputDelimited_8", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_8";

				int tos_count_tFileInputDelimited_8 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_8 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_8 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_8 = null;
				int limit_tFileInputDelimited_8 = -1;
				try {

					Object filename_tFileInputDelimited_8 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commandes.csv";
					if (filename_tFileInputDelimited_8 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_8 = 0, random_value_tFileInputDelimited_8 = -1;
						if (footer_value_tFileInputDelimited_8 > 0 || random_value_tFileInputDelimited_8 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_8 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commandes.csv",
								"US-ASCII", ";", "\n", false, 1, 0, limit_tFileInputDelimited_8, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_8_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_8 != null && fid_tFileInputDelimited_8.nextRecord()) {
						rowstate_tFileInputDelimited_8.reset();

						row9 = null;

						row9 = null;

						boolean whetherReject_tFileInputDelimited_8 = false;
						row9 = new row9Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_8 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_8 = 0;

							temp = fid_tFileInputDelimited_8.get(columnIndexWithD_tFileInputDelimited_8);
							if (temp.length() > 0) {

								try {

									row9.IDCmd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_8) {
									globalMap.put("tFileInputDelimited_8_ERROR_MESSAGE",
											ex_tFileInputDelimited_8.getMessage());
									rowstate_tFileInputDelimited_8.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDCmd", "row9", temp, ex_tFileInputDelimited_8),
											ex_tFileInputDelimited_8));
								}

							} else {

								row9.IDCmd = null;

							}

							columnIndexWithD_tFileInputDelimited_8 = 1;

							temp = fid_tFileInputDelimited_8.get(columnIndexWithD_tFileInputDelimited_8);
							if (temp.length() > 0) {

								try {

									row9.IDClt = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_8) {
									globalMap.put("tFileInputDelimited_8_ERROR_MESSAGE",
											ex_tFileInputDelimited_8.getMessage());
									rowstate_tFileInputDelimited_8.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDClt", "row9", temp, ex_tFileInputDelimited_8),
											ex_tFileInputDelimited_8));
								}

							} else {

								row9.IDClt = null;

							}

							columnIndexWithD_tFileInputDelimited_8 = 2;

							temp = fid_tFileInputDelimited_8.get(columnIndexWithD_tFileInputDelimited_8);
							if (temp.length() > 0) {

								try {

									row9.IDVend = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_8) {
									globalMap.put("tFileInputDelimited_8_ERROR_MESSAGE",
											ex_tFileInputDelimited_8.getMessage());
									rowstate_tFileInputDelimited_8.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDVend", "row9", temp, ex_tFileInputDelimited_8),
											ex_tFileInputDelimited_8));
								}

							} else {

								row9.IDVend = null;

							}

							columnIndexWithD_tFileInputDelimited_8 = 3;

							temp = fid_tFileInputDelimited_8.get(columnIndexWithD_tFileInputDelimited_8);
							if (temp.length() > 0) {

								try {

									row9.dateCmd = ParserUtils.parseTo_Date(temp, "dd/MM/yyyy");

								} catch (java.lang.Exception ex_tFileInputDelimited_8) {
									globalMap.put("tFileInputDelimited_8_ERROR_MESSAGE",
											ex_tFileInputDelimited_8.getMessage());
									rowstate_tFileInputDelimited_8.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"dateCmd", "row9", temp, ex_tFileInputDelimited_8),
											ex_tFileInputDelimited_8));
								}

							} else {

								row9.dateCmd = null;

							}

							if (rowstate_tFileInputDelimited_8.getException() != null) {
								throw rowstate_tFileInputDelimited_8.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_8_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_8 = true;

							System.err.println(e.getMessage());
							row9 = null;

						}

						/**
						 * [tFileInputDelimited_8 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_8 main ] start
						 */

						currentComponent = "tFileInputDelimited_8";

						tos_count_tFileInputDelimited_8++;

						/**
						 * [tFileInputDelimited_8 main ] stop
						 */

						/**
						 * [tFileInputDelimited_8 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_8";

						/**
						 * [tFileInputDelimited_8 process_data_begin ] stop
						 */
// Start of branch "row9"
						if (row9 != null) {

							/**
							 * [tAdvancedHash_row9 main ] start
							 */

							currentComponent = "tAdvancedHash_row9";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row9"

								);
							}

							row9Struct row9_HashRow = new row9Struct();

							row9_HashRow.IDCmd = row9.IDCmd;

							row9_HashRow.IDClt = row9.IDClt;

							row9_HashRow.IDVend = row9.IDVend;

							row9_HashRow.dateCmd = row9.dateCmd;

							tHash_Lookup_row9.put(row9_HashRow);

							tos_count_tAdvancedHash_row9++;

							/**
							 * [tAdvancedHash_row9 main ] stop
							 */

							/**
							 * [tAdvancedHash_row9 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row9";

							/**
							 * [tAdvancedHash_row9 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row9 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row9";

							/**
							 * [tAdvancedHash_row9 process_data_end ] stop
							 */

						} // End of branch "row9"

						/**
						 * [tFileInputDelimited_8 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_8";

						/**
						 * [tFileInputDelimited_8 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_8 end ] start
						 */

						currentComponent = "tFileInputDelimited_8";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commandes.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_8 != null) {
							fid_tFileInputDelimited_8.close();
						}
					}
					if (fid_tFileInputDelimited_8 != null) {
						globalMap.put("tFileInputDelimited_8_NB_LINE", fid_tFileInputDelimited_8.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_8", true);
				end_Hash.put("tFileInputDelimited_8", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_8 end ] stop
				 */

				/**
				 * [tAdvancedHash_row9 end ] start
				 */

				currentComponent = "tAdvancedHash_row9";

				tHash_Lookup_row9.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row9");
				}

				ok_Hash.put("tAdvancedHash_row9", true);
				end_Hash.put("tAdvancedHash_row9", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row9 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_8 finally ] start
				 */

				currentComponent = "tFileInputDelimited_8";

				/**
				 * [tFileInputDelimited_8 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row9 finally ] start
				 */

				currentComponent = "tAdvancedHash_row9";

				/**
				 * [tAdvancedHash_row9 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_8_SUBPROCESS_STATE", 1);
	}

	public static class row10Struct implements routines.system.IPersistableComparableLookupRow<row10Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer IDClt;

		public Integer getIDClt() {
			return this.IDClt;
		}

		public String nomClt;

		public String getNomClt() {
			return this.nomClt;
		}

		public String prenomClt;

		public String getPrenomClt() {
			return this.prenomClt;
		}

		public String adresseClt;

		public String getAdresseClt() {
			return this.adresseClt;
		}

		public String villeClt;

		public String getVilleClt() {
			return this.villeClt;
		}

		public String etatClt;

		public String getEtatClt() {
			return this.etatClt;
		}

		public String _V11;

		public String get_V11() {
			return this._V11;
		}

		public String depClt;

		public String getDepClt() {
			return this.depClt;
		}

		public String regionClt;

		public String getRegionClt() {
			return this.regionClt;
		}

		public String paysClt;

		public String getPaysClt() {
			return this.paysClt;
		}

		public String telClt;

		public String getTelClt() {
			return this.telClt;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.IDClt == null) ? 0 : this.IDClt.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row10Struct other = (row10Struct) obj;

			if (this.IDClt == null) {
				if (other.IDClt != null)
					return false;

			} else if (!this.IDClt.equals(other.IDClt))

				return false;

			return true;
		}

		public void copyDataTo(row10Struct other) {

			other.IDClt = this.IDClt;
			other.nomClt = this.nomClt;
			other.prenomClt = this.prenomClt;
			other.adresseClt = this.adresseClt;
			other.villeClt = this.villeClt;
			other.etatClt = this.etatClt;
			other._V11 = this._V11;
			other.depClt = this.depClt;
			other.regionClt = this.regionClt;
			other.paysClt = this.paysClt;
			other.telClt = this.telClt;

		}

		public void copyKeysDataTo(row10Struct other) {

			other.IDClt = this.IDClt;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDClt = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDClt = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDClt, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDClt, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				this.nomClt = readString(dis, ois);

				this.prenomClt = readString(dis, ois);

				this.adresseClt = readString(dis, ois);

				this.villeClt = readString(dis, ois);

				this.etatClt = readString(dis, ois);

				this._V11 = readString(dis, ois);

				this.depClt = readString(dis, ois);

				this.regionClt = readString(dis, ois);

				this.paysClt = readString(dis, ois);

				this.telClt = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				this.nomClt = readString(dis, objectIn);

				this.prenomClt = readString(dis, objectIn);

				this.adresseClt = readString(dis, objectIn);

				this.villeClt = readString(dis, objectIn);

				this.etatClt = readString(dis, objectIn);

				this._V11 = readString(dis, objectIn);

				this.depClt = readString(dis, objectIn);

				this.regionClt = readString(dis, objectIn);

				this.paysClt = readString(dis, objectIn);

				this.telClt = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				writeString(this.nomClt, dos, oos);

				writeString(this.prenomClt, dos, oos);

				writeString(this.adresseClt, dos, oos);

				writeString(this.villeClt, dos, oos);

				writeString(this.etatClt, dos, oos);

				writeString(this._V11, dos, oos);

				writeString(this.depClt, dos, oos);

				writeString(this.regionClt, dos, oos);

				writeString(this.paysClt, dos, oos);

				writeString(this.telClt, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				writeString(this.nomClt, dos, objectOut);

				writeString(this.prenomClt, dos, objectOut);

				writeString(this.adresseClt, dos, objectOut);

				writeString(this.villeClt, dos, objectOut);

				writeString(this.etatClt, dos, objectOut);

				writeString(this._V11, dos, objectOut);

				writeString(this.depClt, dos, objectOut);

				writeString(this.regionClt, dos, objectOut);

				writeString(this.paysClt, dos, objectOut);

				writeString(this.telClt, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDClt=" + String.valueOf(IDClt));
			sb.append(",nomClt=" + nomClt);
			sb.append(",prenomClt=" + prenomClt);
			sb.append(",adresseClt=" + adresseClt);
			sb.append(",villeClt=" + villeClt);
			sb.append(",etatClt=" + etatClt);
			sb.append(",_V11=" + _V11);
			sb.append(",depClt=" + depClt);
			sb.append(",regionClt=" + regionClt);
			sb.append(",paysClt=" + paysClt);
			sb.append(",telClt=" + telClt);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row10Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.IDClt, other.IDClt);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_9Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_9_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row10Struct row10 = new row10Struct();

				/**
				 * [tAdvancedHash_row10 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row10", false);
				start_Hash.put("tAdvancedHash_row10", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row10";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row10");
				}

				int tos_count_tAdvancedHash_row10 = 0;

				// connection name:row10
				// source node:tFileInputDelimited_9 - inputs:(after_tFileInputDelimited_6)
				// outputs:(row10,row10) | target node:tAdvancedHash_row10 - inputs:(row10)
				// outputs:()
				// linked node: tMap_3 - inputs:(row7,row8,row9,row10)
				// outputs:(Commande_LIG_Coul)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row10 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row10Struct> tHash_Lookup_row10 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row10Struct>getLookup(matchingModeEnum_row10);

				globalMap.put("tHash_Lookup_row10", tHash_Lookup_row10);

				/**
				 * [tAdvancedHash_row10 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_9 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_9", false);
				start_Hash.put("tFileInputDelimited_9", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_9";

				int tos_count_tFileInputDelimited_9 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_9 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_9 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_9 = null;
				int limit_tFileInputDelimited_9 = -1;
				try {

					Object filename_tFileInputDelimited_9 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Clients.csv";
					if (filename_tFileInputDelimited_9 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_9 = 0, random_value_tFileInputDelimited_9 = -1;
						if (footer_value_tFileInputDelimited_9 > 0 || random_value_tFileInputDelimited_9 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_9 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Clients.csv",
								"UTF-8", ";", "\n", false, 1, 0, limit_tFileInputDelimited_9, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_9 != null && fid_tFileInputDelimited_9.nextRecord()) {
						rowstate_tFileInputDelimited_9.reset();

						row10 = null;

						row10 = null;

						boolean whetherReject_tFileInputDelimited_9 = false;
						row10 = new row10Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_9 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_9 = 0;

							temp = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);
							if (temp.length() > 0) {

								try {

									row10.IDClt = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_9) {
									globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE",
											ex_tFileInputDelimited_9.getMessage());
									rowstate_tFileInputDelimited_9.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDClt", "row10", temp, ex_tFileInputDelimited_9),
											ex_tFileInputDelimited_9));
								}

							} else {

								row10.IDClt = null;

							}

							columnIndexWithD_tFileInputDelimited_9 = 1;

							row10.nomClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 2;

							row10.prenomClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 3;

							row10.adresseClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 4;

							row10.villeClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 5;

							row10.etatClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 6;

							row10._V11 = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 7;

							row10.depClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 8;

							row10.regionClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 9;

							row10.paysClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							columnIndexWithD_tFileInputDelimited_9 = 10;

							row10.telClt = fid_tFileInputDelimited_9.get(columnIndexWithD_tFileInputDelimited_9);

							if (rowstate_tFileInputDelimited_9.getException() != null) {
								throw rowstate_tFileInputDelimited_9.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_9_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_9 = true;

							System.err.println(e.getMessage());
							row10 = null;

						}

						/**
						 * [tFileInputDelimited_9 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_9 main ] start
						 */

						currentComponent = "tFileInputDelimited_9";

						tos_count_tFileInputDelimited_9++;

						/**
						 * [tFileInputDelimited_9 main ] stop
						 */

						/**
						 * [tFileInputDelimited_9 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_9";

						/**
						 * [tFileInputDelimited_9 process_data_begin ] stop
						 */
// Start of branch "row10"
						if (row10 != null) {

							/**
							 * [tAdvancedHash_row10 main ] start
							 */

							currentComponent = "tAdvancedHash_row10";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row10"

								);
							}

							row10Struct row10_HashRow = new row10Struct();

							row10_HashRow.IDClt = row10.IDClt;

							row10_HashRow.nomClt = row10.nomClt;

							row10_HashRow.prenomClt = row10.prenomClt;

							row10_HashRow.adresseClt = row10.adresseClt;

							row10_HashRow.villeClt = row10.villeClt;

							row10_HashRow.etatClt = row10.etatClt;

							row10_HashRow._V11 = row10._V11;

							row10_HashRow.depClt = row10.depClt;

							row10_HashRow.regionClt = row10.regionClt;

							row10_HashRow.paysClt = row10.paysClt;

							row10_HashRow.telClt = row10.telClt;

							tHash_Lookup_row10.put(row10_HashRow);

							tos_count_tAdvancedHash_row10++;

							/**
							 * [tAdvancedHash_row10 main ] stop
							 */

							/**
							 * [tAdvancedHash_row10 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row10";

							/**
							 * [tAdvancedHash_row10 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row10 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row10";

							/**
							 * [tAdvancedHash_row10 process_data_end ] stop
							 */

						} // End of branch "row10"

						/**
						 * [tFileInputDelimited_9 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_9";

						/**
						 * [tFileInputDelimited_9 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_9 end ] start
						 */

						currentComponent = "tFileInputDelimited_9";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Clients.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_9 != null) {
							fid_tFileInputDelimited_9.close();
						}
					}
					if (fid_tFileInputDelimited_9 != null) {
						globalMap.put("tFileInputDelimited_9_NB_LINE", fid_tFileInputDelimited_9.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_9", true);
				end_Hash.put("tFileInputDelimited_9", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_9 end ] stop
				 */

				/**
				 * [tAdvancedHash_row10 end ] start
				 */

				currentComponent = "tAdvancedHash_row10";

				tHash_Lookup_row10.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row10");
				}

				ok_Hash.put("tAdvancedHash_row10", true);
				end_Hash.put("tAdvancedHash_row10", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row10 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_9 finally ] start
				 */

				currentComponent = "tFileInputDelimited_9";

				/**
				 * [tFileInputDelimited_9 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row10 finally ] start
				 */

				currentComponent = "tAdvancedHash_row10";

				/**
				 * [tAdvancedHash_row10 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_9_SUBPROCESS_STATE", 1);
	}

	public static class Cmd_LIGStruct implements routines.system.IPersistableRow<Cmd_LIGStruct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDPrd;

		public Integer getIDPrd() {
			return this.IDPrd;
		}

		public Integer qtt;

		public Integer getQtt() {
			return this.qtt;
		}

		public Integer prixVenteInitial;

		public Integer getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public Float prixVenteEUR;

		public Float getPrixVenteEUR() {
			return this.prixVenteEUR;
		}

		public Float prixVenteUSD;

		public Float getPrixVenteUSD() {
			return this.prixVenteUSD;
		}

		public Float prixVenteJPY;

		public Float getPrixVenteJPY() {
			return this.prixVenteJPY;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prixVenteEUR = null;
					} else {
						this.prixVenteEUR = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.prixVenteUSD = null;
					} else {
						this.prixVenteUSD = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.prixVenteJPY = null;
					} else {
						this.prixVenteJPY = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.prixVenteEUR = null;
					} else {
						this.prixVenteEUR = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.prixVenteUSD = null;
					} else {
						this.prixVenteUSD = dis.readFloat();
					}

					length = dis.readByte();
					if (length == -1) {
						this.prixVenteJPY = null;
					} else {
						this.prixVenteJPY = dis.readFloat();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.prixVenteEUR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prixVenteEUR);
				}

				// Float

				if (this.prixVenteUSD == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prixVenteUSD);
				}

				// Float

				if (this.prixVenteJPY == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prixVenteJPY);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// Float

				if (this.prixVenteEUR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prixVenteEUR);
				}

				// Float

				if (this.prixVenteUSD == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prixVenteUSD);
				}

				// Float

				if (this.prixVenteJPY == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.prixVenteJPY);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDPrd=" + String.valueOf(IDPrd));
			sb.append(",qtt=" + String.valueOf(qtt));
			sb.append(",prixVenteInitial=" + String.valueOf(prixVenteInitial));
			sb.append(",Couleur=" + Couleur);
			sb.append(",prixVenteEUR=" + String.valueOf(prixVenteEUR));
			sb.append(",prixVenteUSD=" + String.valueOf(prixVenteUSD));
			sb.append(",prixVenteJPY=" + String.valueOf(prixVenteJPY));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Cmd_LIGStruct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row11Struct implements routines.system.IPersistableRow<row11Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDPrd;

		public Integer getIDPrd() {
			return this.IDPrd;
		}

		public Integer qtt;

		public Integer getQtt() {
			return this.qtt;
		}

		public Integer prixVenteInitial;

		public Integer getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public String paysClt;

		public String getPaysClt() {
			return this.paysClt;
		}

		public Integer annee;

		public Integer getAnnee() {
			return this.annee;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					this.paysClt = readString(dis);

					this.annee = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					this.paysClt = readString(dis);

					this.annee = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.paysClt, dos);

				// Integer

				writeInteger(this.annee, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.paysClt, dos);

				// Integer

				writeInteger(this.annee, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDPrd=" + String.valueOf(IDPrd));
			sb.append(",qtt=" + String.valueOf(qtt));
			sb.append(",prixVenteInitial=" + String.valueOf(prixVenteInitial));
			sb.append(",Couleur=" + Couleur);
			sb.append(",paysClt=" + paysClt);
			sb.append(",annee=" + String.valueOf(annee));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row11Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class after_tFileInputDelimited_10Struct
			implements routines.system.IPersistableRow<after_tFileInputDelimited_10Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];

		public Integer IDCmd;

		public Integer getIDCmd() {
			return this.IDCmd;
		}

		public Integer IDPrd;

		public Integer getIDPrd() {
			return this.IDPrd;
		}

		public Integer qtt;

		public Integer getQtt() {
			return this.qtt;
		}

		public Integer prixVenteInitial;

		public Integer getPrixVenteInitial() {
			return this.prixVenteInitial;
		}

		public String Couleur;

		public String getCouleur() {
			return this.Couleur;
		}

		public String paysClt;

		public String getPaysClt() {
			return this.paysClt;
		}

		public Integer annee;

		public Integer getAnnee() {
			return this.annee;
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					this.paysClt = readString(dis);

					this.annee = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.IDCmd = readInteger(dis);

					this.IDPrd = readInteger(dis);

					this.qtt = readInteger(dis);

					this.prixVenteInitial = readInteger(dis);

					this.Couleur = readString(dis);

					this.paysClt = readString(dis);

					this.annee = readInteger(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.paysClt, dos);

				// Integer

				writeInteger(this.annee, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.IDCmd, dos);

				// Integer

				writeInteger(this.IDPrd, dos);

				// Integer

				writeInteger(this.qtt, dos);

				// Integer

				writeInteger(this.prixVenteInitial, dos);

				// String

				writeString(this.Couleur, dos);

				// String

				writeString(this.paysClt, dos);

				// Integer

				writeInteger(this.annee, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("IDCmd=" + String.valueOf(IDCmd));
			sb.append(",IDPrd=" + String.valueOf(IDPrd));
			sb.append(",qtt=" + String.valueOf(qtt));
			sb.append(",prixVenteInitial=" + String.valueOf(prixVenteInitial));
			sb.append(",Couleur=" + Couleur);
			sb.append(",paysClt=" + paysClt);
			sb.append(",annee=" + String.valueOf(annee));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(after_tFileInputDelimited_10Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_10Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				tFileInputDelimited_11Process(globalMap);

				row11Struct row11 = new row11Struct();
				Cmd_LIGStruct Cmd_LIG = new Cmd_LIGStruct();

				/**
				 * [tFileOutputDelimited_11 begin ] start
				 */

				ok_Hash.put("tFileOutputDelimited_11", false);
				start_Hash.put("tFileOutputDelimited_11", System.currentTimeMillis());

				currentComponent = "tFileOutputDelimited_11";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Cmd_LIG");
				}

				int tos_count_tFileOutputDelimited_11 = 0;

				String fileName_tFileOutputDelimited_11 = "";
				fileName_tFileOutputDelimited_11 = (new java.io.File(
						"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Cmd_LIG.csv"))
								.getAbsolutePath().replace("\\", "/");
				String fullName_tFileOutputDelimited_11 = null;
				String extension_tFileOutputDelimited_11 = null;
				String directory_tFileOutputDelimited_11 = null;
				if ((fileName_tFileOutputDelimited_11.indexOf("/") != -1)) {
					if (fileName_tFileOutputDelimited_11.lastIndexOf(".") < fileName_tFileOutputDelimited_11
							.lastIndexOf("/")) {
						fullName_tFileOutputDelimited_11 = fileName_tFileOutputDelimited_11;
						extension_tFileOutputDelimited_11 = "";
					} else {
						fullName_tFileOutputDelimited_11 = fileName_tFileOutputDelimited_11.substring(0,
								fileName_tFileOutputDelimited_11.lastIndexOf("."));
						extension_tFileOutputDelimited_11 = fileName_tFileOutputDelimited_11
								.substring(fileName_tFileOutputDelimited_11.lastIndexOf("."));
					}
					directory_tFileOutputDelimited_11 = fileName_tFileOutputDelimited_11.substring(0,
							fileName_tFileOutputDelimited_11.lastIndexOf("/"));
				} else {
					if (fileName_tFileOutputDelimited_11.lastIndexOf(".") != -1) {
						fullName_tFileOutputDelimited_11 = fileName_tFileOutputDelimited_11.substring(0,
								fileName_tFileOutputDelimited_11.lastIndexOf("."));
						extension_tFileOutputDelimited_11 = fileName_tFileOutputDelimited_11
								.substring(fileName_tFileOutputDelimited_11.lastIndexOf("."));
					} else {
						fullName_tFileOutputDelimited_11 = fileName_tFileOutputDelimited_11;
						extension_tFileOutputDelimited_11 = "";
					}
					directory_tFileOutputDelimited_11 = "";
				}
				boolean isFileGenerated_tFileOutputDelimited_11 = true;
				java.io.File filetFileOutputDelimited_11 = new java.io.File(fileName_tFileOutputDelimited_11);
				globalMap.put("tFileOutputDelimited_11_FILE_NAME", fileName_tFileOutputDelimited_11);
				int nb_line_tFileOutputDelimited_11 = 0;
				int splitedFileNo_tFileOutputDelimited_11 = 0;
				int currentRow_tFileOutputDelimited_11 = 0;

				final String OUT_DELIM_tFileOutputDelimited_11 = /**
																	 * Start field
																	 * tFileOutputDelimited_11:FIELDSEPARATOR
																	 */
						";"/** End field tFileOutputDelimited_11:FIELDSEPARATOR */
				;

				final String OUT_DELIM_ROWSEP_tFileOutputDelimited_11 = /**
																		 * Start field
																		 * tFileOutputDelimited_11:ROWSEPARATOR
																		 */
						"\n"/** End field tFileOutputDelimited_11:ROWSEPARATOR */
				;

				// create directory only if not exists
				if (directory_tFileOutputDelimited_11 != null
						&& directory_tFileOutputDelimited_11.trim().length() != 0) {
					java.io.File dir_tFileOutputDelimited_11 = new java.io.File(directory_tFileOutputDelimited_11);
					if (!dir_tFileOutputDelimited_11.exists()) {
						dir_tFileOutputDelimited_11.mkdirs();
					}
				}

				// routines.system.Row
				java.io.Writer outtFileOutputDelimited_11 = null;

				java.io.File fileToDelete_tFileOutputDelimited_11 = new java.io.File(fileName_tFileOutputDelimited_11);
				if (fileToDelete_tFileOutputDelimited_11.exists()) {
					fileToDelete_tFileOutputDelimited_11.delete();
				}
				outtFileOutputDelimited_11 = new java.io.BufferedWriter(new java.io.OutputStreamWriter(
						new java.io.FileOutputStream(fileName_tFileOutputDelimited_11, false), "ISO-8859-15"));
				if (filetFileOutputDelimited_11.length() == 0) {
					outtFileOutputDelimited_11.write("IDCmd");
					outtFileOutputDelimited_11.write(OUT_DELIM_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.write("IDPrd");
					outtFileOutputDelimited_11.write(OUT_DELIM_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.write("qtt");
					outtFileOutputDelimited_11.write(OUT_DELIM_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.write("prixVenteInitial");
					outtFileOutputDelimited_11.write(OUT_DELIM_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.write("Couleur");
					outtFileOutputDelimited_11.write(OUT_DELIM_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.write("prixVenteEUR");
					outtFileOutputDelimited_11.write(OUT_DELIM_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.write("prixVenteUSD");
					outtFileOutputDelimited_11.write(OUT_DELIM_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.write("prixVenteJPY");
					outtFileOutputDelimited_11.write(OUT_DELIM_ROWSEP_tFileOutputDelimited_11);
					outtFileOutputDelimited_11.flush();
				}

				resourceMap.put("out_tFileOutputDelimited_11", outtFileOutputDelimited_11);
				resourceMap.put("nb_line_tFileOutputDelimited_11", nb_line_tFileOutputDelimited_11);

				/**
				 * [tFileOutputDelimited_11 begin ] stop
				 */

				/**
				 * [tMap_4 begin ] start
				 */

				ok_Hash.put("tMap_4", false);
				start_Hash.put("tMap_4", System.currentTimeMillis());

				currentComponent = "tMap_4";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row11");
				}

				int tos_count_tMap_4 = 0;

// ###############################
// # Lookup's keys initialization

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = (org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) ((org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct>) globalMap
						.get("tHash_Lookup_row12"));

				row12Struct row12HashKey = new row12Struct();
				row12Struct row12Default = new row12Struct();
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_4__Struct {
				}
				Var__tMap_4__Struct Var__tMap_4 = new Var__tMap_4__Struct();
// ###############################

// ###############################
// # Outputs initialization
				Cmd_LIGStruct Cmd_LIG_tmp = new Cmd_LIGStruct();
// ###############################

				/**
				 * [tMap_4 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_10 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_10", false);
				start_Hash.put("tFileInputDelimited_10", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_10";

				int tos_count_tFileInputDelimited_10 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_10 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_10 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_10 = null;
				int limit_tFileInputDelimited_10 = -1;
				try {

					Object filename_tFileInputDelimited_10 = "C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG_Coul.csv";
					if (filename_tFileInputDelimited_10 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_10 = 0, random_value_tFileInputDelimited_10 = -1;
						if (footer_value_tFileInputDelimited_10 > 0 || random_value_tFileInputDelimited_10 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_10 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG_Coul.csv",
								"US-ASCII", ";", "\n", false, 1, 0, limit_tFileInputDelimited_10, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_10 != null && fid_tFileInputDelimited_10.nextRecord()) {
						rowstate_tFileInputDelimited_10.reset();

						row11 = null;

						boolean whetherReject_tFileInputDelimited_10 = false;
						row11 = new row11Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_10 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_10 = 0;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row11.IDCmd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDCmd", "row11", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row11.IDCmd = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 1;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row11.IDPrd = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"IDPrd", "row11", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row11.IDPrd = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 2;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row11.qtt = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"qtt", "row11", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row11.qtt = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 3;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row11.prixVenteInitial = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"prixVenteInitial", "row11", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row11.prixVenteInitial = null;

							}

							columnIndexWithD_tFileInputDelimited_10 = 4;

							row11.Couleur = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);

							columnIndexWithD_tFileInputDelimited_10 = 5;

							row11.paysClt = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);

							columnIndexWithD_tFileInputDelimited_10 = 6;

							temp = fid_tFileInputDelimited_10.get(columnIndexWithD_tFileInputDelimited_10);
							if (temp.length() > 0) {

								try {

									row11.annee = ParserUtils.parseTo_Integer(temp);

								} catch (java.lang.Exception ex_tFileInputDelimited_10) {
									globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE",
											ex_tFileInputDelimited_10.getMessage());
									rowstate_tFileInputDelimited_10.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"annee", "row11", temp, ex_tFileInputDelimited_10),
											ex_tFileInputDelimited_10));
								}

							} else {

								row11.annee = null;

							}

							if (rowstate_tFileInputDelimited_10.getException() != null) {
								throw rowstate_tFileInputDelimited_10.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_10_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_10 = true;

							System.err.println(e.getMessage());
							row11 = null;

						}

						/**
						 * [tFileInputDelimited_10 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_10 main ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						tos_count_tFileInputDelimited_10++;

						/**
						 * [tFileInputDelimited_10 main ] stop
						 */

						/**
						 * [tFileInputDelimited_10 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						/**
						 * [tFileInputDelimited_10 process_data_begin ] stop
						 */
// Start of branch "row11"
						if (row11 != null) {

							/**
							 * [tMap_4 main ] start
							 */

							currentComponent = "tMap_4";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row11"

								);
							}

							boolean hasCasePrimitiveKeyWithNull_tMap_4 = false;

							// ###############################
							// # Input tables (lookups)
							boolean rejectedInnerJoin_tMap_4 = false;
							boolean mainRowRejected_tMap_4 = false;

							///////////////////////////////////////////////
							// Starting Lookup Table "row12"
							///////////////////////////////////////////////

							boolean forceLooprow12 = false;

							row12Struct row12ObjectFromLookup = null;

							if (!rejectedInnerJoin_tMap_4) { // G_TM_M_020

								hasCasePrimitiveKeyWithNull_tMap_4 = false;

								row12HashKey.Annee = row11.annee;

								row12HashKey.Devise = row11.paysClt;

								row12HashKey.hashCodeDirty = true;

								tHash_Lookup_row12.lookup(row12HashKey);

							} // G_TM_M_020

							if (tHash_Lookup_row12 != null && tHash_Lookup_row12.getCount(row12HashKey) > 1) { // G 071

								// System.out.println("WARNING: UNIQUE MATCH is configured for the lookup
								// 'row12' and it contains more one result from keys : row12.Annee = '" +
								// row12HashKey.Annee + "', row12.Devise = '" + row12HashKey.Devise + "'");
							} // G 071

							row12Struct row12 = null;

							row12Struct fromLookup_row12 = null;
							row12 = row12Default;

							if (tHash_Lookup_row12 != null && tHash_Lookup_row12.hasNext()) { // G 099

								fromLookup_row12 = tHash_Lookup_row12.next();

							} // G 099

							if (fromLookup_row12 != null) {
								row12 = fromLookup_row12;
							}

							// ###############################
							{ // start of Var scope

								// ###############################
								// # Vars tables

								Var__tMap_4__Struct Var = Var__tMap_4;// ###############################
								// ###############################
								// # Output tables

								Cmd_LIG = null;

// # Output table : 'Cmd_LIG'
								Cmd_LIG_tmp.IDCmd = row11.IDCmd;
								Cmd_LIG_tmp.IDPrd = row11.IDPrd;
								Cmd_LIG_tmp.qtt = row11.qtt;
								Cmd_LIG_tmp.prixVenteInitial = row11.prixVenteInitial;
								Cmd_LIG_tmp.Couleur = row11.Couleur;
								Cmd_LIG_tmp.prixVenteEUR = row11.paysClt.equals("EUR")
										? row11.prixVenteInitial * row12.EUR
										: row11.prixVenteInitial;
								Cmd_LIG_tmp.prixVenteUSD = row11.paysClt.equals("USD")
										? row11.prixVenteInitial * row12.USD
										: row11.prixVenteInitial;
								Cmd_LIG_tmp.prixVenteJPY = row11.paysClt.equals("JPY")
										? row11.prixVenteInitial * row12.JPY
										: row11.prixVenteInitial;
								Cmd_LIG = Cmd_LIG_tmp;
// ###############################

							} // end of Var scope

							rejectedInnerJoin_tMap_4 = false;

							tos_count_tMap_4++;

							/**
							 * [tMap_4 main ] stop
							 */

							/**
							 * [tMap_4 process_data_begin ] start
							 */

							currentComponent = "tMap_4";

							/**
							 * [tMap_4 process_data_begin ] stop
							 */
// Start of branch "Cmd_LIG"
							if (Cmd_LIG != null) {

								/**
								 * [tFileOutputDelimited_11 main ] start
								 */

								currentComponent = "tFileOutputDelimited_11";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "Cmd_LIG"

									);
								}

								StringBuilder sb_tFileOutputDelimited_11 = new StringBuilder();
								if (Cmd_LIG.IDCmd != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.IDCmd);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_tFileOutputDelimited_11);
								if (Cmd_LIG.IDPrd != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.IDPrd);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_tFileOutputDelimited_11);
								if (Cmd_LIG.qtt != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.qtt);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_tFileOutputDelimited_11);
								if (Cmd_LIG.prixVenteInitial != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.prixVenteInitial);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_tFileOutputDelimited_11);
								if (Cmd_LIG.Couleur != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.Couleur);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_tFileOutputDelimited_11);
								if (Cmd_LIG.prixVenteEUR != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.prixVenteEUR);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_tFileOutputDelimited_11);
								if (Cmd_LIG.prixVenteUSD != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.prixVenteUSD);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_tFileOutputDelimited_11);
								if (Cmd_LIG.prixVenteJPY != null) {
									sb_tFileOutputDelimited_11.append(Cmd_LIG.prixVenteJPY);
								}
								sb_tFileOutputDelimited_11.append(OUT_DELIM_ROWSEP_tFileOutputDelimited_11);

								nb_line_tFileOutputDelimited_11++;
								resourceMap.put("nb_line_tFileOutputDelimited_11", nb_line_tFileOutputDelimited_11);

								outtFileOutputDelimited_11.write(sb_tFileOutputDelimited_11.toString());

								tos_count_tFileOutputDelimited_11++;

								/**
								 * [tFileOutputDelimited_11 main ] stop
								 */

								/**
								 * [tFileOutputDelimited_11 process_data_begin ] start
								 */

								currentComponent = "tFileOutputDelimited_11";

								/**
								 * [tFileOutputDelimited_11 process_data_begin ] stop
								 */

								/**
								 * [tFileOutputDelimited_11 process_data_end ] start
								 */

								currentComponent = "tFileOutputDelimited_11";

								/**
								 * [tFileOutputDelimited_11 process_data_end ] stop
								 */

							} // End of branch "Cmd_LIG"

							/**
							 * [tMap_4 process_data_end ] start
							 */

							currentComponent = "tMap_4";

							/**
							 * [tMap_4 process_data_end ] stop
							 */

						} // End of branch "row11"

						/**
						 * [tFileInputDelimited_10 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_10";

						/**
						 * [tFileInputDelimited_10 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_10 end ] start
						 */

						currentComponent = "tFileInputDelimited_10";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Talend/TOS_DI-20211109_1610-V8.0.1/TOS_DI-20211109_1610-V8.0.1/workspace/Commande_LIG_Coul.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_10 != null) {
							fid_tFileInputDelimited_10.close();
						}
					}
					if (fid_tFileInputDelimited_10 != null) {
						globalMap.put("tFileInputDelimited_10_NB_LINE", fid_tFileInputDelimited_10.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_10", true);
				end_Hash.put("tFileInputDelimited_10", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_10 end ] stop
				 */

				/**
				 * [tMap_4 end ] start
				 */

				currentComponent = "tMap_4";

// ###############################
// # Lookup hashes releasing
				if (tHash_Lookup_row12 != null) {
					tHash_Lookup_row12.endGet();
				}
				globalMap.remove("tHash_Lookup_row12");

// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row11");
				}

				ok_Hash.put("tMap_4", true);
				end_Hash.put("tMap_4", System.currentTimeMillis());

				/**
				 * [tMap_4 end ] stop
				 */

				/**
				 * [tFileOutputDelimited_11 end ] start
				 */

				currentComponent = "tFileOutputDelimited_11";

				if (outtFileOutputDelimited_11 != null) {
					outtFileOutputDelimited_11.flush();
					outtFileOutputDelimited_11.close();
				}

				globalMap.put("tFileOutputDelimited_11_NB_LINE", nb_line_tFileOutputDelimited_11);
				globalMap.put("tFileOutputDelimited_11_FILE_NAME", fileName_tFileOutputDelimited_11);

				resourceMap.put("finish_tFileOutputDelimited_11", true);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Cmd_LIG");
				}

				ok_Hash.put("tFileOutputDelimited_11", true);
				end_Hash.put("tFileOutputDelimited_11", System.currentTimeMillis());

				/**
				 * [tFileOutputDelimited_11 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			// free memory for "tMap_4"
			globalMap.remove("tHash_Lookup_row12");

			try {

				/**
				 * [tFileInputDelimited_10 finally ] start
				 */

				currentComponent = "tFileInputDelimited_10";

				/**
				 * [tFileInputDelimited_10 finally ] stop
				 */

				/**
				 * [tMap_4 finally ] start
				 */

				currentComponent = "tMap_4";

				/**
				 * [tMap_4 finally ] stop
				 */

				/**
				 * [tFileOutputDelimited_11 finally ] start
				 */

				currentComponent = "tFileOutputDelimited_11";

				if (resourceMap.get("finish_tFileOutputDelimited_11") == null) {

					java.io.Writer outtFileOutputDelimited_11 = (java.io.Writer) resourceMap
							.get("out_tFileOutputDelimited_11");
					if (outtFileOutputDelimited_11 != null) {
						outtFileOutputDelimited_11.flush();
						outtFileOutputDelimited_11.close();
					}

				}

				/**
				 * [tFileOutputDelimited_11 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", 1);
	}

	public static class row12Struct implements routines.system.IPersistableComparableLookupRow<row12Struct> {
		final static byte[] commonByteArrayLock_ETUDE_DE_CAS_Etude_cas = new byte[0];
		static byte[] commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public Integer Annee;

		public Integer getAnnee() {
			return this.Annee;
		}

		public String Devise;

		public String getDevise() {
			return this.Devise;
		}

		public Float EUR;

		public Float getEUR() {
			return this.EUR;
		}

		public Float JPY;

		public Float getJPY() {
			return this.JPY;
		}

		public Float USD;

		public Float getUSD() {
			return this.USD;
		}

		public String Column5;

		public String getColumn5() {
			return this.Column5;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Annee == null) ? 0 : this.Annee.hashCode());

				result = prime * result + ((this.Devise == null) ? 0 : this.Devise.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final row12Struct other = (row12Struct) obj;

			if (this.Annee == null) {
				if (other.Annee != null)
					return false;

			} else if (!this.Annee.equals(other.Annee))

				return false;

			if (this.Devise == null) {
				if (other.Devise != null)
					return false;

			} else if (!this.Devise.equals(other.Devise))

				return false;

			return true;
		}

		public void copyDataTo(row12Struct other) {

			other.Annee = this.Annee;
			other.Devise = this.Devise;
			other.EUR = this.EUR;
			other.JPY = this.JPY;
			other.USD = this.USD;
			other.Column5 = this.Column5;

		}

		public void copyKeysDataTo(row12Struct other) {

			other.Annee = this.Annee;
			other.Devise = this.Devise;

		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_ETUDE_DE_CAS_Etude_cas.length) {
					if (length < 1024 && commonByteArray_ETUDE_DE_CAS_Etude_cas.length == 0) {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[1024];
					} else {
						commonByteArray_ETUDE_DE_CAS_Etude_cas = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length);
				strReturn = new String(commonByteArray_ETUDE_DE_CAS_Etude_cas, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private String readString(DataInputStream dis, ObjectInputStream ois) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				dis.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private String readString(DataInputStream dis, org.jboss.marshalling.Unmarshaller unmarshaller)
				throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				byte[] byteArray = new byte[length];
				unmarshaller.read(byteArray);
				strReturn = new String(byteArray, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, DataOutputStream dos, org.jboss.marshalling.Marshaller marshaller)
				throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private void writeString(String str, DataOutputStream dos, ObjectOutputStream oos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		public void readKeysData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Devise = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readKeysData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_ETUDE_DE_CAS_Etude_cas) {

				try {

					int length = 0;

					this.Annee = readInteger(dis);

					this.Devise = readString(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeKeysData(ObjectOutputStream dos) {
			try {

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Devise, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeKeysData(org.jboss.marshalling.Marshaller dos) {
			try {

				// Integer

				writeInteger(this.Annee, dos);

				// String

				writeString(this.Devise, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		/**
		 * Fill Values data by reading ObjectInputStream.
		 */
		public void readValuesData(DataInputStream dis, ObjectInputStream ois) {
			try {

				int length = 0;

				length = dis.readByte();
				if (length == -1) {
					this.EUR = null;
				} else {
					this.EUR = dis.readFloat();
				}

				length = dis.readByte();
				if (length == -1) {
					this.JPY = null;
				} else {
					this.JPY = dis.readFloat();
				}

				length = dis.readByte();
				if (length == -1) {
					this.USD = null;
				} else {
					this.USD = dis.readFloat();
				}

				this.Column5 = readString(dis, ois);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		public void readValuesData(DataInputStream dis, org.jboss.marshalling.Unmarshaller objectIn) {
			try {
				int length = 0;

				length = objectIn.readByte();
				if (length == -1) {
					this.EUR = null;
				} else {
					this.EUR = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.JPY = null;
				} else {
					this.JPY = objectIn.readFloat();
				}

				length = objectIn.readByte();
				if (length == -1) {
					this.USD = null;
				} else {
					this.USD = objectIn.readFloat();
				}

				this.Column5 = readString(dis, objectIn);

			} catch (IOException e) {
				throw new RuntimeException(e);

			}

		}

		/**
		 * Return a byte array which represents Values data.
		 */
		public void writeValuesData(DataOutputStream dos, ObjectOutputStream oos) {
			try {

				if (this.EUR == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.EUR);
				}

				if (this.JPY == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.JPY);
				}

				if (this.USD == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeFloat(this.USD);
				}

				writeString(this.Column5, dos, oos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeValuesData(DataOutputStream dos, org.jboss.marshalling.Marshaller objectOut) {
			try {

				if (this.EUR == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.EUR);
				}

				if (this.JPY == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.JPY);
				}

				if (this.USD == null) {
					objectOut.writeByte(-1);
				} else {
					objectOut.writeByte(0);
					objectOut.writeFloat(this.USD);
				}

				writeString(this.Column5, dos, objectOut);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public boolean supportMarshaller() {
			return true;
		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Annee=" + String.valueOf(Annee));
			sb.append(",Devise=" + Devise);
			sb.append(",EUR=" + String.valueOf(EUR));
			sb.append(",JPY=" + String.valueOf(JPY));
			sb.append(",USD=" + String.valueOf(USD));
			sb.append(",Column5=" + Column5);
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row12Struct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Annee, other.Annee);
			if (returnValue != 0) {
				return returnValue;
			}

			returnValue = checkNullsAndCompare(this.Devise, other.Devise);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputDelimited_11Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputDelimited_11_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row12Struct row12 = new row12Struct();

				/**
				 * [tAdvancedHash_row12 begin ] start
				 */

				ok_Hash.put("tAdvancedHash_row12", false);
				start_Hash.put("tAdvancedHash_row12", System.currentTimeMillis());

				currentComponent = "tAdvancedHash_row12";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row12");
				}

				int tos_count_tAdvancedHash_row12 = 0;

				// connection name:row12
				// source node:tFileInputDelimited_11 - inputs:(after_tFileInputDelimited_10)
				// outputs:(row12,row12) | target node:tAdvancedHash_row12 - inputs:(row12)
				// outputs:()
				// linked node: tMap_4 - inputs:(row11,row12) outputs:(Cmd_LIG)

				org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE matchingModeEnum_row12 = org.talend.designer.components.lookup.common.ICommonLookup.MATCHING_MODE.UNIQUE_MATCH;

				org.talend.designer.components.lookup.memory.AdvancedMemoryLookup<row12Struct> tHash_Lookup_row12 = org.talend.designer.components.lookup.memory.AdvancedMemoryLookup
						.<row12Struct>getLookup(matchingModeEnum_row12);

				globalMap.put("tHash_Lookup_row12", tHash_Lookup_row12);

				/**
				 * [tAdvancedHash_row12 begin ] stop
				 */

				/**
				 * [tFileInputDelimited_11 begin ] start
				 */

				ok_Hash.put("tFileInputDelimited_11", false);
				start_Hash.put("tFileInputDelimited_11", System.currentTimeMillis());

				currentComponent = "tFileInputDelimited_11";

				int tos_count_tFileInputDelimited_11 = 0;

				final routines.system.RowState rowstate_tFileInputDelimited_11 = new routines.system.RowState();

				int nb_line_tFileInputDelimited_11 = 0;
				org.talend.fileprocess.FileInputDelimited fid_tFileInputDelimited_11 = null;
				int limit_tFileInputDelimited_11 = -1;
				try {

					Object filename_tFileInputDelimited_11 = "C:/Users/LnD/Documents/Formation Data/Qlick - Talend/Etude de cas -  M2I Vehicules/Etude de cas -  M2I Vehicules/TauxChange.csv";
					if (filename_tFileInputDelimited_11 instanceof java.io.InputStream) {

						int footer_value_tFileInputDelimited_11 = 0, random_value_tFileInputDelimited_11 = -1;
						if (footer_value_tFileInputDelimited_11 > 0 || random_value_tFileInputDelimited_11 > 0) {
							throw new java.lang.Exception(
									"When the input source is a stream,footer and random shouldn't be bigger than 0.");
						}

					}
					try {
						fid_tFileInputDelimited_11 = new org.talend.fileprocess.FileInputDelimited(
								"C:/Users/LnD/Documents/Formation Data/Qlick - Talend/Etude de cas -  M2I Vehicules/Etude de cas -  M2I Vehicules/TauxChange.csv",
								"US-ASCII", ";", "\n", false, 1, 0, limit_tFileInputDelimited_11, -1, false);
					} catch (java.lang.Exception e) {
						globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE", e.getMessage());

						System.err.println(e.getMessage());

					}

					while (fid_tFileInputDelimited_11 != null && fid_tFileInputDelimited_11.nextRecord()) {
						rowstate_tFileInputDelimited_11.reset();

						row12 = null;

						row12 = null;

						boolean whetherReject_tFileInputDelimited_11 = false;
						row12 = new row12Struct();
						try {

							int columnIndexWithD_tFileInputDelimited_11 = 0;

							String temp = "";

							columnIndexWithD_tFileInputDelimited_11 = 0;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row12.Annee = ParserUtils
											.parseTo_Integer(ParserUtils.parseTo_Number(temp, ',', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"Annee", "row12", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row12.Annee = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 1;

							row12.Devise = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);

							columnIndexWithD_tFileInputDelimited_11 = 2;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row12.EUR = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(temp, ',', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"EUR", "row12", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row12.EUR = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 3;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row12.JPY = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(temp, ',', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"JPY", "row12", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row12.JPY = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 4;

							temp = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);
							if (temp.length() > 0) {

								try {

									row12.USD = ParserUtils.parseTo_Float(ParserUtils.parseTo_Number(temp, ',', '.'));

								} catch (java.lang.Exception ex_tFileInputDelimited_11) {
									globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE",
											ex_tFileInputDelimited_11.getMessage());
									rowstate_tFileInputDelimited_11.setException(new RuntimeException(String.format(
											"Couldn't parse value for column '%s' in '%s', value is '%s'. Details: %s",
											"USD", "row12", temp, ex_tFileInputDelimited_11),
											ex_tFileInputDelimited_11));
								}

							} else {

								row12.USD = null;

							}

							columnIndexWithD_tFileInputDelimited_11 = 5;

							row12.Column5 = fid_tFileInputDelimited_11.get(columnIndexWithD_tFileInputDelimited_11);

							if (rowstate_tFileInputDelimited_11.getException() != null) {
								throw rowstate_tFileInputDelimited_11.getException();
							}

						} catch (java.lang.Exception e) {
							globalMap.put("tFileInputDelimited_11_ERROR_MESSAGE", e.getMessage());
							whetherReject_tFileInputDelimited_11 = true;

							System.err.println(e.getMessage());
							row12 = null;

						}

						/**
						 * [tFileInputDelimited_11 begin ] stop
						 */

						/**
						 * [tFileInputDelimited_11 main ] start
						 */

						currentComponent = "tFileInputDelimited_11";

						tos_count_tFileInputDelimited_11++;

						/**
						 * [tFileInputDelimited_11 main ] stop
						 */

						/**
						 * [tFileInputDelimited_11 process_data_begin ] start
						 */

						currentComponent = "tFileInputDelimited_11";

						/**
						 * [tFileInputDelimited_11 process_data_begin ] stop
						 */
// Start of branch "row12"
						if (row12 != null) {

							/**
							 * [tAdvancedHash_row12 main ] start
							 */

							currentComponent = "tAdvancedHash_row12";

							if (execStat) {
								runStat.updateStatOnConnection(iterateId, 1, 1

										, "row12"

								);
							}

							row12Struct row12_HashRow = new row12Struct();

							row12_HashRow.Annee = row12.Annee;

							row12_HashRow.Devise = row12.Devise;

							row12_HashRow.EUR = row12.EUR;

							row12_HashRow.JPY = row12.JPY;

							row12_HashRow.USD = row12.USD;

							row12_HashRow.Column5 = row12.Column5;

							tHash_Lookup_row12.put(row12_HashRow);

							tos_count_tAdvancedHash_row12++;

							/**
							 * [tAdvancedHash_row12 main ] stop
							 */

							/**
							 * [tAdvancedHash_row12 process_data_begin ] start
							 */

							currentComponent = "tAdvancedHash_row12";

							/**
							 * [tAdvancedHash_row12 process_data_begin ] stop
							 */

							/**
							 * [tAdvancedHash_row12 process_data_end ] start
							 */

							currentComponent = "tAdvancedHash_row12";

							/**
							 * [tAdvancedHash_row12 process_data_end ] stop
							 */

						} // End of branch "row12"

						/**
						 * [tFileInputDelimited_11 process_data_end ] start
						 */

						currentComponent = "tFileInputDelimited_11";

						/**
						 * [tFileInputDelimited_11 process_data_end ] stop
						 */

						/**
						 * [tFileInputDelimited_11 end ] start
						 */

						currentComponent = "tFileInputDelimited_11";

					}
				} finally {
					if (!((Object) ("C:/Users/LnD/Documents/Formation Data/Qlick - Talend/Etude de cas -  M2I Vehicules/Etude de cas -  M2I Vehicules/TauxChange.csv") instanceof java.io.InputStream)) {
						if (fid_tFileInputDelimited_11 != null) {
							fid_tFileInputDelimited_11.close();
						}
					}
					if (fid_tFileInputDelimited_11 != null) {
						globalMap.put("tFileInputDelimited_11_NB_LINE", fid_tFileInputDelimited_11.getRowNumber());

					}
				}

				ok_Hash.put("tFileInputDelimited_11", true);
				end_Hash.put("tFileInputDelimited_11", System.currentTimeMillis());

				/**
				 * [tFileInputDelimited_11 end ] stop
				 */

				/**
				 * [tAdvancedHash_row12 end ] start
				 */

				currentComponent = "tAdvancedHash_row12";

				tHash_Lookup_row12.endPut();

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row12");
				}

				ok_Hash.put("tAdvancedHash_row12", true);
				end_Hash.put("tAdvancedHash_row12", System.currentTimeMillis());

				/**
				 * [tAdvancedHash_row12 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputDelimited_11 finally ] start
				 */

				currentComponent = "tFileInputDelimited_11";

				/**
				 * [tFileInputDelimited_11 finally ] stop
				 */

				/**
				 * [tAdvancedHash_row12 finally ] start
				 */

				currentComponent = "tAdvancedHash_row12";

				/**
				 * [tAdvancedHash_row12 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputDelimited_11_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Etude_cas Etude_casClass = new Etude_cas();

		int exitCode = Etude_casClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Etude_cas.class.getClassLoader()
					.getResourceAsStream("etude_de_cas/etude_cas_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Etude_cas.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputDelimited_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_1) {
			globalMap.put("tFileInputDelimited_1_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_1.printStackTrace();

		}
		try {
			errorCode = null;
			tFileInputDelimited_10Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputDelimited_10) {
			globalMap.put("tFileInputDelimited_10_SUBPROCESS_STATE", -1);

			e_tFileInputDelimited_10.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : Etude_cas");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 538519 characters generated by Talend Open Studio for Data Integration on the
 * 29 février 2024 à 16:29:03 CET
 ************************************************************************************************/