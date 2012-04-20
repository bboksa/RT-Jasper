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

You should now be able to configure RT DataSources as well as define RTRIQL (RT Rest Interface Query Language) queries.

### Further information

For further information please refert to the [RT-Jasper Wiki](https://github.com/bboksa/RT-Jasper/wiki).
