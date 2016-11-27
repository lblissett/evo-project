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
g5 <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung)) + geom_line(aes(y =mydata5$bester.Fitnesswert.Real, col="Reelle Werte")) +geom_line(aes(y=mydata5$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata5$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5 + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y =
"Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 5 Genen (stabile Population)")

g20 <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung)) + geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata20$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20 + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y =
"Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 20 Genen (stabile Population)")

g50 <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Kodierung)) + geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="Reele Werte")) + geom_line(aes(y=mydata50$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata50$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50 + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 50 Genen (stabile Population)")

g5worst <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata5$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata5$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata5$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5worst + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y =
 "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 5 Genen (wachsende Population)")

g20worst <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata20$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata20$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata20$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20worst + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y =
 "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 20 Genen (wachsende Population)")

g50worst <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata50$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata50$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata50$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50worst + theme() + theme(panel.grid.major = element_line(color = "black",
linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 50 Genen (wachsende Population)")

mydata5 <- cbind (mydata5, "Range1P" = mydata5$worstBinaer1P - mydata5$Binaer1P)
mydata5 <- cbind (mydata5, "Range2P" = mydata5$worstBinaer2P - mydata5$Binaer2P)
mydata5 <- cbind (mydata5, "RangeReal" = mydata5$worstReal - mydata5$bester.Fitnesswert.Real)

g5worst <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung))  + geom_bar(stat="count")
g5worst + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Mittlerer Fitnesswertverlauf mit 5 Genen (wachsende Population)")

ggplot(data=mydata5, aes(x=mydata5$Zyklus, y=mydata5$RangeReal)) + geom_bar(stat="identity")

mydata5 = read.csv("mean100_n5_isGrowing_true.csv", header=TRUE)
mydata20 = read.csv("mean100_n20_isGrowing_true.csv", header=TRUE)
mydata50 = read.csv("mean100_n50_isGrowing_true.csv", header=TRUE)
library(ggplot2)
limitsX = c(0, 500)
limitsY = c(0, 75)
stepsX = c(0,100,200,300,400,500)
stepsY = c(0,1, 10,20,30,40,50,60,70)
g5 <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung)) + geom_line(aes(y =mydata5$bester.Fitnesswert.Real, col="Reelle Werte")) +geom_line(aes(y=mydata5$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata5$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 5 Genen")

g20 <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung)) + geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata20$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 20 Genen")

g50 <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Kodierung)) + geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="Reele Werte")) + geom_line(aes(y=mydata50$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata50$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.80, .90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 50 Genen")

g5worst <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata5$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata5$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata5$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5worst + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 5 Genen")

g20worst <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung))  + geom_line(aes(y=mydata20$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata20$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata20$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20worst + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 20 Genen")

g50worst <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Codierung))  + geom_line(aes(y=mydata50$worstBinaer1P, col="worstBinaer1P"))+ geom_line(aes(y=mydata50$worstBinaer2P, col="worstBinaer2P")) + geom_line(aes(y=mydata50$worstReal, col="worstReal")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50worst + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position = c(0.80,0.90)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 50 Genen")

mydata5 <- cbind (mydata5, "Range1P" = mydata5$worstBinaer1P - mydata5$Binaer1P)
mydata5 <- cbind (mydata5, "Range2P" = mydata5$worstBinaer2P - mydata5$Binaer2P)
mydata5 <- cbind (mydata5, "RangeReal" = mydata5$worstReal - mydata5$bester.Fitnesswert.Real)

