wd <- getwd()
project <- "/IdeaProjects/evo-project/src/data/results/"
path <- paste(wd, project, sep="/")
setwd(path)
mydata5 = read.csv("mean100_n5_isGrowing_false.csv", header=TRUE)
mydata20 = read.csv("mean100_n20_isGrowing_false.csv", header=TRUE)
mydata50 = read.csv("mean100_n50_isGrowing_false.csv", header=TRUE)
library(ggplot2)
limitsX = c(0, 500)
limitsY = c(0, 75)
stepsX = c(0,100,200,300,400,500)
stepsY = c(0,1, 10,20,30,40,50,60,70)
g5 <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, col=Kodierung)) + geom_line(aes(y =mydata5$bester.Fitnesswert.Real, col="Reelle Werte n=5",linetype="dashed")) +geom_line(aes(y=mydata5$Binaer1P, col="Binär1P n=5"), linetype="dotted")+ geom_line(aes(y=mydata5$Binaer2P, col = "Binär2P n=5")) + geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte n=20"),linetype="dashed") + geom_line(aes(y=mydata20$Binaer1P, col="Binär1P n=20"), linetype="dotted")+ geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P n=20"))+ geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="Reele Werte n=50"), linetype="dashed") + geom_line(aes(y=mydata50$Binaer1P, col="Binär1P n=50"), linetype="dotted")+ geom_line(aes(y=mydata50$Binaer2P, col = "Binär2P n=50")) + scale_x_continuous(limits=limitsX, stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5 + theme() + theme(panel.grid.major = element_line(color = "black",linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y ="Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 5 Genen (stabile Population)")

g20 <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung)) + geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata20$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20 + theme() + theme(panel.grid.major = element_line(color = "black",linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y ="Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 20 Genen (stabile Population)")
ggsave("../best-20-stable.jpeg")


g50 <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Kodierung)) + geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata50$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata50$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50 + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 50 Genen (stabile Population)")

g5worst <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata5$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata5$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata5$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5worst + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y =
 "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 5 Genen (stabile Population)")

g20worst <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Codierung))  + geom_line(aes(y=mydata20$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata20$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata20$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20worst + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y =
 "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 20 Genen (stabile Population)")

g50worst <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata50$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata50$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata50$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50worst + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 50 Genen (stabile Population)")

mydata5 <- cbind (mydata5, "Range1P" = mydata5$worstBinaer1P - mydata5$Binaer1P)
mydata5 <- cbind (mydata5, "Range2P" = mydata5$worstBinaer2P - mydata5$Binaer2P)
mydata5 <- cbind (mydata5, "RangeReal" = mydata5$worstReal - mydata5$bester.Fitnesswert.Real)


mydata5 = read.csv("mean100_n5_isGrowing_true.csv", header=TRUE)
mydata20 = read.csv("mean100_n20_isGrowing_true.csv", header=TRUE)
mydata50 = read.csv("mean100_n50_isGrowing_true.csv", header=TRUE)
library(ggplot2)
limitsX = c(0, 500)
limitsY = c(0, 75)
stepsX = c(0,100,200,300,400,500)
stepsY = c(0,1, 10,20,30,40,50,60,70)
g5 <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung)) + geom_line(aes(y =mydata5$bester.Fitnesswert.Real, col="Reelle Werte")) +geom_line(aes(y=mydata5$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata5$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 5 Genen (wachsende Population")

g20 <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung)) + geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata20$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 20 Genen (wachsende Population)")
ggsave("../best-20-grow.jpeg")

g50 <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Kodierung)) + geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata50$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata50$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 50 Genen (wachsende Population")

g5worst <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata5$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata5$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata5$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5worst + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 5 Genen (wachsende Population")

g20worst <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata20$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata20$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata20$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20worst + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 20 Genen (wachsende Population")

g50worst <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Codierung))  + geom_line(aes(y=mydata50$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata50$Binaer1P, col="bestBinaer1P")) + geom_line(aes(y=mydata50$worstReal, col="worstReal")) + geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="bestReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50worst + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Bester und schlechtester Fitnessverlauf mit 50 Genen (wachsende Population)")

mydata5 <- cbind (mydata5, "Range1P" = mydata5$worstBinaer1P - mydata5$Binaer1P)
mydata5 <- cbind (mydata5, "Range2P" = mydata5$worstBinaer2P - mydata5$Binaer2P)
mydata5 <- cbind (mydata5, "RangeReel" = mydata5$worstReal - mydata5$bester.Fitnesswert.Real)

ggsave("../best-and-worst.jpeg")

limitsY = c(0, 140)
stepsY = c(0,1, 10,20,30,40,50,60,70,80,90,100, 110,120,130,140)

g50worst <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, fill=Kodierung))+
geom_ribbon(aes(ymin=mydata50$Binaer1P, ymax=mydata50$worstBinaer1P, fill="Binaer1P"))+
geom_ribbon(aes(ymin=mydata50$Binaer2P, ymax=mydata50$worstBinaer2P, fill="Binaer2P"))  +
geom_ribbon(aes(ymin=mydata50$bester.Fitnesswert.Real, ymax=mydata50$worstReal+0.5,fill="Real")) +
scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
scale_fill_manual(name = "Kodierung", values =c('Binaer1P'='orange','Binaer2P'='red', 'Real'='black'))


g50worst + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
panel.grid.major = element_line(color = "black",
linetype= "dotted"), legend.text = element_text(size = 20), legend.position=c(.80, .90),
legend.title = element_text(size=16, face="bold")) +
labs(size=20, x = "Zyklus",y = "Fitnesswert",
title = "Schlechtester Fitnesswertverlauf (n = 50)")

smydata5 = read.csv("mean100_n5_isGrowing_false.csv", header=TRUE)
smydata20 = read.csv("mean100_n20_isGrowing_false.csv", header=TRUE)
smydata50 = read.csv("mean100_n50_isGrowing_false.csv", header=TRUE)

stepsY = c(0,10,20,30,40,50,60,70)
limitsY = c(0, 75)

s5 <- ggplot(data=smydata5, aes(x=mydata50$Zyklus, color=Kodierung)) +
geom_line(aes(y=smydata5$bester.Fitnesswert.Real, col="n = 5")) +
geom_line(aes(y=smydata20$bester.Fitnesswert.Real, col="n = 20"))+
geom_line(aes(y=smydata50$bester.Fitnesswert.Real, col = "n = 50")) +
scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
s5 + theme() + theme(panel.grid.major = element_line(color = "black",
linetype= "dotted"), legend.position=c(.80, .90)) +
legend.title = element_text(colour="blue", size=16, face="bold") + labs(x =
"Zyklus",y =
"Fitnesswert", title = "Bester Fitnesswertverlauf (reelle Kodierung)")

customTheme = list(theme(plot.title = element_text(size = 20, hjust = 0.5),
axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
panel.grid.major = element_line(color = "black",
linetype= "dotted"), legend.text = element_text(size = 20), legend.position=c(.80, .90), legend.title = element_text(size=16,
face="bold")) + labs(size=20, x = "Zyklus",y =
"Fitnesswert", title = "Bester Fitnesswertverlauf (Binärkodierung 1-Punkt)"))

ggplot(data=smydata5, aes(x=mydata50$Zyklus, color=Kodierung)) +
geom_line(aes(y=smydata20$Binaer1P, col="n = 5"), size=2) +
geom_line(aes(y=smydata5$Binaer1P, col="n = 20"), size=2)+
geom_line(aes(y=smydata50$Binaer1P, col = "n = 50"), size=2) +
scale_x_continuous(limits=limitsX, breaks=stepsX) +
scale_y_continuous(limits=limitsY, breaks=stepsY) +
scale_colour_discrete(name = "Kodierung", breaks=c("n = 5","n = 20","n = 50")) + customTheme

s5 <- ggplot(data=smydata5, aes(x=mydata50$Zyklus, color=Kodierung)) +
geom_line(aes(y=smydata5$Binaer2P, col="n = 5")) +
geom_line(aes(y=smydata20$Binaer2P, col="n = 20"))+
geom_line(aes(y=smydata50$Binaer2P, col = "n = 50")) +
scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
s5 + theme() + theme(panel.grid.major = element_line(color = "black",
linetype= "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y =
"Fitnesswert", title = "Bester Fitnesswertverlauf (Binärkodierung 2-Punkt)")

