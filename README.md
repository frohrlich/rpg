## Commandes utiles

Pour commit sa progression :

```
git add .
git commit -m "Commentaire"
git push
```

git
Pour récupérer les données d'un autre :

```
git pull
```

Pour changer de branche :

```
git checkout nomdelabranche
```

Pour vérifier sur quelle branche on est / si on est synchronisé avec le repository :

```
git status
```

## Marche à suivre pour ajouter une nouvelle fonctionnalité (cas le plus simple sans conflit) :

D'abord se mettre sur dev et récupérer les derniers commits :

```
git checkout dev
git pull
```

Puis créer sa nouvelle branche :

```
git checkout -b nomdelafonctionnalite
git push --set-upstream origin nomdelafonctionnalite
```

Ensuite coder dessus !
Ensuite commit et push ses changements. A ce stade demander à quelqu'un de vérifier que tout est ok.
Ensuite merge avec dev (on ne touche pas à main pour le moment) :

```
git checkout dev
git fetch
git pull
git merge nomdelafonctionnalite
git branch -d nomdelafonctionnalite
git push
git push origin --delete nomdelafonctionnalite
```
