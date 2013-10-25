整体说明
========

所有主要内容分布于engine和standard两个包中。engine包含一组表达基本概念的接口，和调用这组接口的代码；而standard提供了在正常的游戏中这组接口的实现，并为实际使用方便进行了一些不影响基本逻辑的扩展。出于方便和实用考虑，下面直接对应用了standard实现的engine进行介绍。

基本概念
--------

游戏位于一个三维空间中，各点由z、x、y三个整数坐标表示。由原点和最大坐标形成一个长方体，其中的每个坐标上有一个tile。同时，角色有若干属性（包括但不限于通常理解的属性，可以一般化理解为变量），而游戏结束（成功或失败）的条件是某些变量中的一个大于0（成功）或小于0（失败）。游戏可以操作的唯一动作是要求角色移动到指定坐标点，每个这样的移动会引发一个event，其中包含对角色若干属性值的改变和空间中若干坐标处的tile的改变，以及其他不影响游戏逻辑的附加信息。

使用举例
--------

使用者应通过配置文件构建一个StandardGame对象，并将此对象载入于一个Engine对象中。随后，由玩家指令，通过Engine对象提供的方法对游戏进行操控。一个简单的使用举例如下，其中config为下面提到的配置文件：

	Engine engine = new Engine();
	StandardGame game = new StandardGame(config);
	MyRenderer renderer = (MyRenderer) game.getRenderer();
	Event event = engine.loadGame(game);
	while (true) {
		Coordinate coord = engine.getCurrentCoordinate();
		coord = renderer.renderAndRead(event, engine, coord);
		engine.moveTo(coord);
	}

其他可用方法参见JavaDoc。

配置文件
========

整个配置文件是一个大JSON对象，以一整个字符串的方式接收。此JSON对象中应有以下值，描述如下：

根 - size
--------

JSON对象。描述空间的大小，包含z、x、y三个值，表示三个方向上的尺寸，即最大坐标值 + 1。

根 - attributes
--------

JSON对象。定义所有用到的属性及其初始值。

根 - failure-checks
--------

JSON数组。列出失败条件检查的各属性。

根 - success-checks
--------

JSON数组。列出成功条件检查的各属性。

根 - tiles
--------

3维JSON数组。描述初始状态下空间各坐标的tile，各tile以一个字符串表示。

根 - tile-values
--------

JSON对象。其中各key为tiles中定义的tile名字符串，而值为*StandardTile对象*。

根 - renderer
--------

JSON对象。同时是一个*StandardRenderer对象*。

*XXX对象*
--------

JSON对象。*XXX*为StandardTile、StandardRenderer、FirstEventMixin或RegularTileMixin。StandardTile和StandardRenderer上面已提及，RegularTileMixin是包含于RegularTile（StandardTile的实现）中的功能模块，实际进行event所需的属性/坐标改变的计算，FirstEventMixin类似于RegularTileMixin，但只用于初始event的计算，包含于初始CharacterTile中。

无论是什么对象，其中应包含一个class值，是一个有空构造函数，同时实现*XXX*接口的类名。此JSON对象的使用会从指定的类实例化一个对象，并随即在上面调用initialize方法，并以此JSON对象为其参数。实际使用时除了StandardRenderer，通常无需自行编写实现*XXX*接口的类，而可直接使用预先提供的通用类。以下介绍为各接口提供的通用类：

### StandardRenderer - cn.edu.tsinghua.academic.c00740273.magictower.standard.DummyStandardRenderer ###

一个“什么也不做”的StandardRenderer，如果没有/不需自己的StandardRenderer实现时占位用。

### StandardTile - cn.edu.tsinghua.academic.c00740273.magictower.standard.CharacterTile ###

表示角色踩在上面的tile。需要：

* base：*RegularTile对象*
* mixin：可选，*FirstEventMixin对象*

### StandardTile - cn.edu.tsinghua.academic.c00740273.magictower.standard.RegularTile ###

表示其他所有的tile。需要：

* mixin：可选，*RegularTileMixin对象*
* rendering：可选，可以为任意内容，通过getRenderingData()方法获取

### RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.Accessible ###

提供一个tile“可进入”的性质，即允许角色踩在上面。无参数。

### RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.AttributeChange ###

更改角色的属性值。需要：

* attribute：字符串，需要更改的属性值
* operator：字符串，更改操作的运算符，可选“+”、“*”或“=”
* operand：操作数

### RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.AttributeSelective ###

根据已有不同的属性值选择性执行其他的mixin。需要：

* attribute：字符串，需要检查的属性值
* reference：整数，参考值
* ==
* !=
* <
* >
* <=
* >=：均为可选，*RegularTileMixin对象*，根据属性值与参考值的关系选择执行

### FirstEventMixin或RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.Batch ###

在只接受一个mixin的地方（大部分）执行多个mixin。需要：

* mixins：JSON数组，各值为一个*RegularTileMixin对象*

### RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.Delegate ###

改执行另一个坐标上的进入方法，如同角色直接踩在目标坐标上一样。需要：

* z
* x
* y：目标坐标

### FirstEventMixin或RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.ExtraInformation ###

在事件中设置附加信息。需要：

* key：字符串，附加信息名
* action：字符串，设置时的动作，可选“set”或“add”
	* "set"：覆盖现有信息
	* "add"：在现有List中添加，如现有值不是List，将被覆盖为包含所指定值的List
* value：设置的值，JSON对象将被转为Map< String, Object >，而JSON数组将被转为List< Object >

### RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.Fight ###

计算打斗。需要：

* attack-attribute：字符串，攻击值属性的属性名
* defense-attribute：字符串，防御值属性的属性名
* health-attribute：字符串，生命值属性的属性名
* death-attribute：字符串，特殊属性名，如果攻击比对方的防御低，此值被设置为-1，其他属性值则不变
* attack-opponent：字符串，对方的攻击值
* defense-opponent：字符串，对方的防御值
* health-opponent：字符串，对方的生命值

此RegularTileMixin会添加key为fight-details的附加信息，内容为Map< String, Object >，其中有如下内容：

* opponent-attack-before：Long对象，打斗前对方的攻击值
* opponent-defense-before：Long对象，打斗前对方的防御值
* opponent-health-before：Long对象，打斗前对方的生命值
* self-attack-before：Long对象，打斗前角色的攻击值
* self-defense-before：Long对象，打斗前角色的防御值
* self-health-before：Long对象，打斗前角色的生命值
* quick-death：Long对象，如果攻击比对方的防御低，此值为-1，否则为0

若quick-death为0，还会有：

* opponent-attack-after：Long对象，打斗后对方的攻击值
* opponent-defense-after：Long对象，打斗后对方的防御值
* opponent-health-after：Long对象，打斗后对方的生命值
* self-attack-after：Long对象，打斗后角色的攻击值
* self-defense-after：Long对象，打斗后角色的防御值
* self-health-after：Long对象，打斗后角色的生命值

### RegularTileMixin - cn.edu.tsinghua.academic.c00740273.magictower.standard.mixin.OneTime ###

当角色试图移动到上面时更改本tile。需要：

* next：*RegularTile对象*，本tile“变成的”新tile

常用tile举例
--------

### 墙 ###

一个空的RegularTile即可。

### 空地 ###

在RegularTile上放置Accessible。

### 修改属性的宝物、钥匙、门 ###

使用AttributeChange修改相应的属性，然后使用OneTime使其获取后消失。如果是例如每走一次就损耗一些生命的tile，不放OneTime即可。

### 常规怪物 ###

使用Fight打斗，使用若干AttributeChange增加经验、金钱等，最后使用OneTime使其消失。

### 上下楼 ###

直接Delegate到另一层楼适当位置的空地即可。

### 显示消息 ###

使用ExtraInformation设置附加信息，并与界面人员协调适当进行显示。

### 商店 ###

增加一个属性，与界面人员协调在用户选择不同选项时设置不同属性值，然后在tile中利用AttributeSelective根据用户选择执行不同操作。

### 交谈后修改另一坐标的内容的NPC ###

增加一个默认置0的属性，目标格使用AttributeSelective在直接触发时无动作（或AttributeChange使值-1并触发游戏失败），而在NPC格中AttributeChange后使用Delegate到目标格使之产生更改。
