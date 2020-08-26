#Définition du problème.

Le problème est la recherche d'un cycle hamiltonien minimal dans un graphe complet

##Problème de base.

Le problème de base est de chercher un cycle minimal parcourant tous les sommets importants 
dans un graphe non orienté.

Une étape étant définie comme un somment du graphe adjacent du sommet précedent,
une solution est donc un sommet initial adjoint d'une succession d'étapes, contenant 
chaque sommet importants au moins une fois, et dont la dernière étape est le sommet 
initial.
Le caractère minimal d'une solution considère que chaque étape a un poid donné, typiquement 1.
il s'agit donc de déterminer la suite d'étapes dont la somme des poids des étape est 
la plus faible.

Le but est de modéliser le problème dans choco, puis déterminer des heuristiques qui 
permettent de trouver et prouver la solution optimale au plus vite.


##Passage du graphe de base à un graphe complet.

Le graphe de base n'est pas complet, cependant il contient des nœuds non intéressants. 
Le but est de passer par tous les nœuds intéressants, et de faire un cycle, tout en 
ayant le moins de distance à parcourir. Le passage ou non par des nœuds non intéressant 
n'est pas détrimental à une solution, tout comme le passage par un même nœud important 
plusieurs fois. Une solution doit donc passer au moins une fois par chaque nœud intéressant.

On définit donc le graphe complet utile comme étant le graphe composé des sommets intéressants 
du graphe de base, et chaque couple de sommets intéressants A,B est relié par une arrête AB 
de distance égale au nombre de sommets parcourus, au minimum, pour aller de A à B. 
Par exemple si mon graphe de base est A-B-C-D et que seuls A et D sont intéressants, 
le graphe complet est donc composé des sommets (A, D) et de l'arrête AD de distance 3.

On démontre facilement que toute solution dans le graphe complet correspond à au moins 
une solution dans le graphe de base de même distance, et réciproquement que toute solution 
dans le graphe de base est associée à une solution dans le graphe complet de taille au plus 
égale. Ainsi, trouver une solution optimale dans le graphe complet, et trouver une solution 
optimale dans le graphe de base, sont équivalents.

Dans la suite on ne travaille que avec le graphe complet.

##Source du cycle.

On appelle source du cycle le premier sommet, et donc aussi la dernière étape. Quand 
on décale toutes les étapes d'un nombre donné(par exemple passer de A-B-C-A à B-C-A-B), 
on a une solution qui a la même distance et qui est aussi valable. On considère donc 
que la source d'une solution n'est pas importante et qu'on peut la choisir arbitrairement, 
du moment qu'elle fait partie des sommets importants.

##Exemples récurrents

Ces exemples de petite taille permettent d'expliciter les problèmes rencontrés.

###Le couloir

Le couloir est un graphe contenant 3 sommets importants, alignés :

    A - B - C


###Le 8

Le 8 est un graphe contenant 6 sommets importants placés en 8 : 

    A - B
    |   |
    C - D
    |   |
    E - F

#Heuristique de recherche.

Quelques alogirthmes permettent de trouver des solutions approchées.

##Heuristique gloutonne.

Une heuristique basique consiste à prendre une source arbitraire, et à itérativement 
ajouter le nœud le plus proche du dernier nœud ajouté et non déjà ajouté. Je note les 
arrêtes ainsi choisies, et tout à la fin j'ajoute l'arrête du dernier nœud ajouté à la 
source pour boucler

Par exemple si mon graphe complet est issu du graphe "couloir" A-B-C-D, et que ma source 
est A, alors j'ajouterai les arrêtes AB, BC, CD et pour boucler, DA.

Cet heuristique n'est pas optimal : en effet, dans [le problème du 8](#le-8), si je pars de C comme source, le nœud le plus proche est D, j'ajouterai donc les arrêtes 
CD, DB, BA, AE, EF, FC pour une distance totale de 8 alors que le parcours CEFDBAC 
serait de taille 6 qui est plus faible.

Cette heuristique permet de trouver une solution "bonne" mais ne permet pas de trouver 
la meilleure.

#Modélisation dans Choco.

Choco est un solveur de problèmes par contraintes, il permet de modéliser un problème 
comme un ensemble de variables ayant chacune un domaine de valeurs possible, et un 
ensemble de relations, ou contraintes, liants ces variables entre elles.

Après une exploration partielle de l'espace des solutions Choco est en mesure de déterminer 
s'il existe ou non une solution, et s'il possède une fonction d'évaluation des solutions, 
il peut ausi déterminer quelle est la meilleure/pire solution possible.

Cela permet d'éviter un parcours total des solutions possibles du problème (bruteforce), 
mais nécessite une modélisation du problème adaptée, et une stratégie d'exploration (heuristique) 
qui peuvent être complexes.

Des modélisations différentes peuvent permettre de définir le problème dans Choco, 
elles sont en faites complémentaires pour arriver à des performances correctes.

##Modélisation des successeurs.

Une modélisation naïve serait de définir, pour chaque nœud, son nœud suivant dans le cycle.

Ce modèle est défini dans Choco par N variable Succ_0 ... Succ_(N-1), toutes différentes et de domaine 
0 .. N-1.

Ce modèle est intuitif et simple mais autorise une solution composée de multiples cycles non 
connectés. Par exemple, dans [le problème du 8](#le-8), ce modèle autoriserait une solution qui serait 
le couple de cycles ABC et DEF.

##Ordonnancement des sommets

Définir le problème comme un réordonnancement des sommets permet de n'obtenir que des 
solutions correctes.

Un tel ordonnacement consiste à définir le problème à N sommets importants comme un 
jeu de N variables {Etape_0, Etape_1, .. Etape_(N-1), Etape_0} représentant les indices des 
sommets de chaque étape. Ainsi la variable Etape_0 est la source du cycle, la variable 
Etape_N est aussi cette source afin de boucler. 
Ces variables doivent ensuite être contraintes à être toutes différentes, en postant 
dans Choco une contrainte AllDifferent.

Cette définition ne permet cependant pas de définir la distance d'une solution, que 
l'on cherche à réduire au maximum. Pour cela il faut mettre en relation ce modèle et 
celui des successeurs.

##Positions des sommets

les variables {Pos_i} représentent la position du ième sommet dans l'itinéraire. La 
position de la source est 0, la position d'un sommet i contrainte par Etape[pos[i]]==i, 
ie une contrainte element dans Choco.

Une fois la position d'un sommet connue, le successeur de celui ci est contraint par 
Succ_i == Etape[pos[i]+1]. Il s'agit encore d'une contrainte element dans choco. Une 
fois cette contrainte posée, la variable Succ_i sera automatiquement déduite d'une 
solution.

##Distance au successeur

La distance d'un sommet i à son successeur, Dist_i, est assurée par une contrainte 
element sur chco : Dist_i = distance_i[Succ_i] , avec distance_i le tableau des distances 
du sommet i aux autres sommets.

Une fois ces distances modélisées, la distance d'une solution est définie comme la 
somme des distances au successeur de chaque sommet : Dist_sol = sum(Dist_i). 

C'est cette variable qui est à réduire dans la résolution du problème Choco.

##Utilisation des arrêtes

Une fois les variables des successeurs définies et contraintes, on peut définir la 
variable d'utilisation d'une arrête dans Choco.

La variable Arr_ij, de valeur 0 ou 1, est définie comme la somme des réification des 
successeurs des sommets i et j entre eux : Arr_ij = (Succ_i == j) + (Succ_j == i) .
Trivialement la variable Arr_ij est égale à Arr_ji , on énumère donc les arretes de 
0 à N×(N-1)/2

    indice(ij) (i>j) = indice(ji)
    indice(ij) (i<j) = j×(j-1)/2+i
    indice(ij) (i=j) est invalide, cette arrête n'existe pas.

Cela donne la matrice d'indices suivants, pour la ième ligne et jème colonne (ici 4 sommets 
de 0 à 3)
    ø 0 1 3
    0 ø 2 4
    1 2 ø 5
    3 4 5 ø
Ici il faut lire, l'arrête du sommet 0 au sommet 1 a l'indice d'arrête 0, tout comme 
l'arrête du sommet 1 au sommet 0 (ce sont les mêmes)

Ce qui nous permet d'énumérer les arrêtes, et les variables d'utilisations, de 0 à 
N×(N-1)/2 -1 , avec N le nombre de sommets.

On voit trivialement que la distance d'une solution est la somme des distances des arrêtes utilisées, 
on peut donc poster dans choco Dist_sol=scalar(Arr_, Dist_arr) avec Dist_arr le vecteur 
des distance de chaque arrete suivant l'indice défini plus haut.

De plus, on sait que exactement N arrêtes sont utilisées, avec N le nombre de sommets, 
on peut de plus poster sum(Arr_)=N

#Heuristiques et optimisation dans choco

##Symétrie du cycle non ordonné

Afin de briser la symétrie induite par la non-orientation du graphe, une contrainte 
d'ordonnancement des sommets avant et après la source peut être posée, par exemple 
Etape(N-1) < Etape(1).

Cette contrainte invalide par exemple dans [le problème du couloir](#le-couloir) la 
solution ABCA, lui préférant la solution équivalente ACBA.

#Heuristique de suppression des arrêtes.

#Contraintes des arrêtes sortantes d'un groupe.
