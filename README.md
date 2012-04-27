## RT-Jasper

![JasperServer - Report Result](https://raw.github.com/wiki/bboksa/RT-Jasper/images/JasperServer%20-%20Report%20Result.png)

### Try RT-Jasper!

You can log into a JasperServer instance and run a report yourself: [Run a demo on JasperServer](https://github.com/bboksa/RT-Jasper/wiki/Run-a-demo-on-JasperServer)!

### Installation

To use RT-Jasper you need to have a working [RT: Request Tracker](http://bestpractical.com/rt/) installation and have command line access to a machine running [JasperServer](http://jasperforge.org/projects/jasperserver).

To use RT-Jasper you need to first install [RT-REST](http://projects.boksa.de/RT-REST "RT-REST") to your local maven repository:

```
git clone git@github.com:bboksa/RT-REST.git
cd RT-REST
mvn install
```

After that you can clone RT-Jasper and build the distribution files:

```
git clone git@github.com:bboksa/RT-Jasper.git
cd RT-Jasper
mvn clean assembly:assembly
```

Now you need to copy the resulting ZIP file to your server:

```
scp distribution/rt-jasper-distribution.zip johndoe@server.example.com:/some/path
```

On your server extract all files from the ZIP:

```
unzip rt-jasper-distribution.zip
```

Take a look at rt-jasper-distribution/build.xml to make sure all properties (espacially webAppDir) are set correctly and then execute:

```
ant -f rt-jasper-distribution/build.xml clean deploy
/etc/init.d/tomcat6 restart
```

### Configure JasperReports Server

Please edit you JasperReports Server according to the following patches:

```diff
--- /var/lib/tomcat6/webapps/jasperserver/WEB-INF/classes/jasperreports.properties.orig  2012-04-19 22:56:24.000000000 +0200
+++ /var/lib/tomcat6/webapps/jasperserver/WEB-INF/classes/jasperreports.properties	2012-04-19 22:58:42.000000000 +0200
@@ -1,5 +1,6 @@
net.sf.jasperreports.query.executer.factory.sql=com.jaspersoft.jasperserver.api.engine.jasperreports.util.JRTimezoneJdbcQueryExecuterFactory
net.sf.jasperreports.query.executer.factory.HiveQL=com.jaspersoft.hadoop.hive.HiveQueryExecuterFactory
+net.sf.jasperreports.query.executer.factory.RTRIQL=de.boksa.jasper.rt.util.JRRTRESTInterfaceQueryExecuterFactory

#limit the crosstab bucket/measure count to prevent out of memory errors
net.sf.jasperreports.crosstab.bucket.measure.limit=100000
```

```diff
--- /var/lib/tomcat6/webapps/jasperserver/WEB-INF/flows/queryBeans.xml.orig  2012-04-19 22:56:16.000000000 +0200
+++ /var/lib/tomcat6/webapps/jasperserver/WEB-INF/flows/queryBeans.xml	2012-04-19 22:57:48.000000000 +0200
@@ -20,6 +20,7 @@
				<value>hql</value>
				<value>domain</value>
                <value>HiveQL</value>
+				<value>RTRIQL</value>
			</list>
		</property>
		<property name="queryLanguagesRequestAttrName" value="queryLanguages"/>
```

### Congratulations

You should now be able to configure RT DataSources as well as define RTRIQL (RT Rest Interface Query Language) queries.

### Further information

For further information please refert to the [RT-Jasper Wiki](https://github.com/bboksa/RT-Jasper/wiki).

***

Copyright (C) 2012  Benjamin Boksa (http://www.boksa.de/).
Licensed under the AGPL, Version 3.0. See LICENSE for details.