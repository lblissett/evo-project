wd <- getwd()
project <- "/IdeaProjects/evo-project/src/data/results/"
path <- paste(wd, project, sep="/")
setwd(path)

# Abbildung Vergleich Kodierungsarten
mydata20 = read.csv("mean100_n20_isGrowing_false.csv", header=TRUE)
library(ggplot2)
library(grid)
limitsX = c(0, 500)
limitsY = c(0, 75)
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

ggsave("../abb1_n20_stable.jpeg")


# Abbildung stabile vs wachsende Population
mydata50s = read.csv("mean100_n50_isGrowing_false.csv", header=TRUE)
mydata50g = read.csv("mean100_n50_isGrowing_true.csv", header=TRUE)

sg50s <- ggplot(data=mydata50s, aes(x=mydata50s$Zyklus, color=Kodierung)) +
geom_line(aes(y =mydata50s$bester.Fitnesswert.Real, col="Reelle Werte"), size=2) +
geom_line(aes(y=mydata50s$Binaer1P, col="Binär1P"), size=2)+
geom_line(aes(y=mydata20$Binaer2P, col = "Binär2P"), size=2) +
scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
scale_colour_discrete(name = "Kodierung", breaks=c("Reelle Werte","Binär1P","Binär2P"))

sg50s + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
panel.grid.major = element_line(color = "black",
linetype= "dotted"), legend.text = element_text(size = 20), legend.position=c(.80, .90), legend.title = element_text(size=16,
face="bold")) + labs(size=20, x = "Zyklus",y =
"Fitnesswert", title = "stabile Population")

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
linetype= "dotted"), legend.text = element_text(size = 20), legend.position=c(.80, .90), legend.title = element_text(size=16,
face="bold")) + labs(size=20, x = "Zyklus",y ="Fitnesswert", title = "wachsende Population")

ggsave("../abb2_stable_growing.jpeg")

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

ggsave("../abb3_different_n.jpeg")


# Abbildung bestes vs schlechtestes Individuum
myDataBW50 = read.csv("mean100_n50_isGrowing_true.csv", header=TRUE)


limitsY = c(0, 140)
stepsY = c(0,1, 10,20,30,40,50,60,70,80,90,100, 110,120,130,140)

bestWorst50 <- ggplot(data=myDataBW50, aes(x=myDataBW50$Zyklus, fill=Kodierung))+
geom_ribbon(aes(ymin=myDataBW50$bester.Fitnesswert.Real, ymax=myDataBW50$worstReal+0.5,fill="Real")) +
geom_ribbon(aes(ymin=myDataBW50$Binaer1P, ymax=myDataBW50$worstBinaer1P, fill="Binaer1P"))+
geom_ribbon(aes(ymin=myDataBW50$Binaer2P, ymax=myDataBW50$worstBinaer2P, fill="Binaer2P"))  +
scale_x_continuous(limits=limitsX, breaks=stepsX) + scale_y_continuous(limits=limitsY, breaks=stepsY) +
scale_colour_discrete(name = "Kodierung", breaks =c('Real'='black','Binaer1P'='orange','Binaer2P'='red'))


bestWorst50 + theme() + theme(plot.title = element_text(size = 20, hjust = 0.5),
axis.title.x =element_text(size = rel(1.5)), axis.text.x =element_text(size = rel(1.5)),
axis.title.y = element_text(size = rel(1.5), angle = 90), axis.text.y =element_text(size = rel(1.5)),
panel.grid.major = element_line(color = "black",
linetype= "dotted"), legend.text = element_text(size = 20),
legend.position=c(.80, .90), legend.title = element_text(size=16,
face="bold")) + labs(size=20, x = "Zyklus",y ="Fitnesswert",
title = "Streuung der Kodierung (n = 50, wachsend)")

ggsave("../abb4_streuung.jpeg")









#
##grid.arrange(sg50s, sg50g, ncol = 2, main = "Vergleich stabile vs. wachsende Population (n = 50)")
#
#multiplot <- function(..., plotlist=NULL, file, cols=1, layout=NULL) {
#    require(grid)
#
#    # Make a list from the ... arguments and plotlist
#    plots <- c(list(...), plotlist)
#
#    numPlots = length(plots)
#
#    # If layout is NULL, then use 'cols' to determine layout
#    if (is.null(layout)) {
#        # Make the panel
#        # ncol: Number of columns of plots
#        # nrow: Number of rows needed, calculated from # of cols
#        layout <- matrix(seq(1, cols * ceiling(numPlots/cols)),
#        ncol = cols, nrow = ceiling(numPlots/cols))
#    }
#
#    if (numPlots==1) {
#        print(plots[[1]])
#
#    } else {
#        # Set up the page
#        grid.newpage()
#        pushViewport(viewport(layout = grid.layout(nrow(layout), ncol(layout))))
#
#        # Make each plot, in the correct location
#        for (i in 1:numPlots) {
#            # Get the i,j matrix positions of the regions that contain this subplot
#            matchidx <- as.data.frame(which(layout == i, arr.ind = TRUE))
#
#            print(plots[[i]], vp = viewport(layout.pos.row = matchidx$row,
#            layout.pos.col = matchidx$col))
#        }
#    }
#}
#
#multiplot(sg50s, sg50g, cols=2)
#
