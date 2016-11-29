wd <- getwd()
project <- "/IdeaProjects/evo-project/src/data/results/"
path <- paste(wd, project, sep="/")
setwd(path)

# Abbildung Vergleich Kodierungsarten
mydata20 = read.csv("mean100_n20_isGrowing_false.csv", header=TRUE)
library(ggplot2)
library(grid)
library(svglite)
limitsX = c(0, 500)
limitsY = c(0, 85)
stepsX = c(0,100,200,300,400,500)
stepsY = c(0,10,20,30,40,50,60,70)

g20 <- ggplot(data=mydata20, aes(x=mydata20$Zyklus, color=Kodierung)) +
  geom_line(aes(y =mydata20$bester.Fitnesswert.Real, col="Reelle Werte"),size=2) +
  geom_line(aes(y=mydata20$Binaer1P, col="Binär1P"), size=2)+
  geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P"), size=2) +
  scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
  scale_colour_discrete(name = "Kodierung", breaks=c("Reelle Werte","Binär1P","Binär2P"))

g20 + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
                      axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
                      axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
                      panel.grid.major = element_line(color = "black",
                                                      linetype= "dotted"), legend.text = element_text(size = 20), legend.position=c(.80, .90), legend.title = element_text(size=16,
                                                                                                                                                                           face="bold")) + labs(size=20, x = "Zyklus",y =
                                                                                                                                                                                                  "Fitnesswert", title = "Vergleich Kodierungsarten (n = 20, stabile Population)")

ggsave("../abb1_n20_stable.svg")


# Abbildung stabile vs wachsende Population
mydata50s = read.csv("mean100_n50_isGrowing_false.csv", header=TRUE)
mydata50g = read.csv("mean100_n50_isGrowing_true.csv", header=TRUE)

sg50s <- ggplot(data=mydata50s, aes(x=mydata50s$Zyklus, color=Kodierung)) +
  geom_line(aes(y =mydata50s$bester.Fitnesswert.Real, col="Reelle Werte",linetype="stabil"), size=2) +
  geom_line(aes(y=mydata50s$Binaer1P, col="Binär1P", linetype="stabil"), size=2) +
  geom_line(aes(y=mydata50s$Binaer2P, col = "Binär2P",linetype="stabil"), size=2) +
  geom_line(aes(y =mydata50g$bester.Fitnesswert.Real, col="Reelle Werte", linetype="wachsend"), size=2) +
  geom_line(aes(y=mydata50g$Binaer1P, col="Binär1P",linetype="wachsend"), size=2)+
  geom_line(aes(y=mydata50g$Binaer2P, col = "Binär2P",linetype="wachsend"),size=2) +
  scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
  scale_colour_discrete(name = "Kodierung", breaks=c("Reelle Werte","Binär1P","Binär2P"))+
  scale_linetype_manual(name="Population",values=c("stabil"="twodash", "wachsend"="solid"))+
  guides(linetype=guide_legend(keywidth = 3, keyheight = 1))


sg50s + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
                        axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
                        axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
                        panel.grid.major = element_line(color = "black",
                        linetype= "dotted"), legend.text = element_text(size = 20), legend.position=c(.80, .90), legend.title = element_text(size=16,face="bold")) + labs(size=20, x = "Zyklus",y ="Fitnesswert", title = "Stabile Population (n = 50)")

#ggsave("../abb2_stable.svg")

sg50g <- ggplot(data=mydata50g, aes(x=mydata50g$Zyklus, color=Kodierung)) +
  geom_line(aes(y =mydata50g$bester.Fitnesswert.Real, col="Reelle Werte"), size=2) +
  geom_line(aes(y=mydata50g$Binaer1P, col="Binär1P"), size=2)+
  geom_line(aes(y=mydata50g$Binaer2P, col = "Binär2P"),size=2) +
  scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
  scale_colour_discrete(name = "Kodierung", breaks=c("Reelle Werte","Binär1P","Binär2P"))

sg50g + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
                        axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
                        axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
                        panel.grid.major = element_line(color = "black",
                        linetype= "dotted"), legend.text = element_text(size = 20), legend.position=c(.80, .90), legend.title = element_text(size=16, face="bold")) + labs(size=20, x = "Zyklus",y ="Fitnesswert", title = "Wachsende Population (n = 50)")

#ggsave("../abb2_growing.svg")

# Abbildung Vergleich n
mydata5n = read.csv("mean100_n5_isGrowing_true.csv", header=TRUE)
mydata20n = read.csv("mean100_n20_isGrowing_true.csv", header=TRUE)
mydata50n = read.csv("mean100_n50_isGrowing_true.csv", header=TRUE)

gn <- ggplot(data=mydata5n, aes(x=mydata5n$Zyklus, color=Kodierung)) +
  geom_line(aes(y=mydata5n$Binaer1P, col="n = 5"), size=2) +
  geom_line(aes(y=mydata20n$Binaer1P, col="n = 20"), size=2)+
  geom_line(aes(y=mydata50n$Binaer1P, col = "n = 50"), size=2) +
  scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
  scale_colour_discrete(name = "Kodierung", breaks=c("n = 5","n = 20","n = 50"))

gn + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
                     axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
                     axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
                     panel.grid.major = element_line(color = "black",
                                                     linetype= "dotted"), legend.text = element_text(size = 20),
                     legend.position=c(.80, .90), legend.title = element_text(size=16,
                                                                              face="bold")) + labs(size=20, x = "Zyklus",y ="Fitnesswert",
                                                                                                   title = "Vergleich Anzahl Gene (Binärkodierung 1-Punkt, wachsende Population)")

ggsave("../abb3_different_n.svg")


# Abbildung bestes vs schlechtestes Individuum
myDataBW50 = read.csv("mean100_n50_isGrowing_true.csv", header=TRUE)


limitsY = c(0, 140)
stepsY = c(0,10,20,30,40,50,60,70,80,90,100, 110,120,130,140)

bestWorst50 <- ggplot(data=myDataBW50, aes(x=myDataBW50$Zyklus, fill=Kodierung))+
  geom_ribbon(aes(ymin=myDataBW50$bester.Fitnesswert.Real, ymax=myDataBW50$worstReal+0.5,fill="Reelle Werte")) +
  geom_ribbon(aes(ymin=myDataBW50$Binaer1P, ymax=myDataBW50$worstBinaer1P, fill="Binaer1P"))+
  geom_ribbon(aes(ymin=myDataBW50$Binaer2P, ymax=myDataBW50$worstBinaer2P, fill="Binaer2P"))  +
  scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
  scale_colour_discrete(name = "Kodierung", breaks =c('Reelle Werte'='black','Binaer1P'='orange','Binaer2P'='red'))


bestWorst50 + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
                              axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
                              axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
                              panel.grid.major = element_line(color = "black",
                                                              linetype= "dotted"), legend.text = element_text(size = 20),
                              legend.position=c(.80, .90), legend.title = element_text(size=16,
                                                                                       face="bold")) + labs(size=20, x = "Zyklus",y ="Fitnesswert",
                                                                                                            title = "Streuung der Kodierung (n = 50, wachsend)")

ggsave("../abb4_streuung.svg")






