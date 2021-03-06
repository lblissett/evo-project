wd <- getwd()
project <- "/IdeaProjects/evo-project/src/data/results/"
path <- paste(wd, project, sep="/")
setwd(path)
ps = 10
rp = 0.9
mp = 0.5
crI = 3
cpc = 20
mydata5 = read.csv(paste("n5_isGrowing_true_ps", ps, "_rp", rp, "_mp", mp, "_crI", crI, "_cpc", cpc, "_trial0.csv"), header=TRUE)
mydata20 = read.csv(paste("n20_isGrowing_true_ps", ps, "_rp", rp, "_mp", mp, "_crI", crI, "_cpc", cpc, "_trial0.csv"), header=TRUE)
mydata50 = read.csv(paste("n50_isGrowing_true_ps", ps, "_rp", rp, "_mp", mp, "_crI", crI, "_cpc", cpc, "_trial0.csv"), header=TRUE)
library(ggplot2)
limitsX = c(0, 300)
limitsY = c(0, 75)
stepsX = c(0,100,200,300)
stepsY = c(0,1, 10,20,30,40,50,60,70)
g5 <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Codierung)) + geom_line(aes(y =mydata5$bester.Fitnesswert.Real, col="Reelle Werte")) +geom_line(aes(y=mydata5$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata5$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.75, .75)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 5 Genen")

g20 <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Codierung)) + geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata20$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.75, .75)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 20 Genen")

g50 <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Codierung)) + geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="Reele Werte")) + geom_line(aes(y=mydata50$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata50$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.75, .75)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 50 Genen")

mydata5 = read.csv(paste("n5_isGrowing_false_ps", ps, "_rp", rp, "_mp", mp, "_crI", crI, "_cpc", cpc, "_trial0.csv"), header=TRUE)
mydata20 = read.csv(paste("n20_isGrowing_false_ps", ps, "_rp", rp, "_mp", mp, "_crI", crI, "_cpc", cpc, "_trial0.csv"), header=TRUE)
mydata50 = read.csv(paste("n50_isGrowing_false_ps", ps, "_rp", rp, "_mp", mp, "_crI", crI, "_cpc", cpc, "_trial0.csv"), header=TRUE)
library(ggplot2)
limitsX = c(0, 300)
limitsY = c(0, 75)
stepsX = c(0,100,200,300)
stepsY = c(0,1, 10,20,30,40,50,60,70)
g5 <- ggplot(data=mydata5, aes(x=mydata5$Zyklus, color=Codierung)) + geom_line(aes(y =mydata5$bester.Fitnesswert.Real, col="Reelle Werte")) +geom_line(aes(y=mydata5$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata5$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g5 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.75, .75)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 5 Genen")

g20 <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Codierung)) + geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte")) + geom_line(aes(y=mydata20$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g20 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.75, .75)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 20 Genen")

g50 <- ggplot(data=mydata50, aes(x=mydata50$Zyklus, color=Codierung)) + geom_line(aes(y=mydata50$bester.Fitnesswert.Real, col="Reele Werte")) + geom_line(aes(y=mydata50$Binaer1P, col="Binär1P"))+ geom_line(aes(y=mydata50$Binaer2P, col = "Binär2P")) + scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY)
g50 + theme() + theme(panel.grid.major = element_line(color = "black", linetype = "dotted"), legend.position=c(.75, .75)) + labs(x = "Zyklus",y = "Fitnesswert", title = "Fitnessverlauf mit 50 Genen")


