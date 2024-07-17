INSERT INTO parcours (nom) VALUES ('Balade près d''HB');

INSERT INTO parcours (nom) VALUES ('Balade autour de la place Gailleton');

SELECT * FROM parcours ORDER BY nom;

SELECT * FROM parcours WHERE nom LIKE '%place%';

SELECT DISTINCT p.id, p.nom
FROM parcours p
INNER JOIN positions pos ON p.id = pos.id
WHERE pos.longitude > 45.75;
