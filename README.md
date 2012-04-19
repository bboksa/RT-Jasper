## RT-Jasper

### Installation

To use RT-Jasper you need to first install [RT-REST](http://bboksa.github.com/RT-REST "RT-REST") to your local maven repository:

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

You should now be able to configure RT Data Sources as well as define RTRIQL (= RT Rest Interface Query Language) queries.

### Further information

For further information please refert to the [RT-Jasper Wiki](https://github.com/bboksa/RT-Jasper/wiki).

***

<a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/"><img alt="Creative Commons License" style="border-width:0" src="http://i.creativecommons.org/l/by-sa/3.0/80x15.png" /></a><br />
<span xmlns:dct="http://purl.org/dc/terms/" property="dct:title">RT-Jasper</span> by <a xmlns:cc="http://creativecommons.org/ns#" href="https://github.com/bboksa" property="cc:attributionName" rel="cc:attributionURL">Benjamin Boksa</a> is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-sa/3.0/">Creative Commons Attribution-ShareAlike 3.0 Unported License</a>.