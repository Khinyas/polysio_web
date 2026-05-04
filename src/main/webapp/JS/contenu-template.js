const PLATEAU = [
                {
                    imageSrc:  'asset/Plateau/Case/0.png',
                    title:     'case + `X`',
                    id: 'plateau',
                    btnText:   'Voir Propriete',
                    btnClass:  'btn-casePlateau'
                },
                {
                    imageSrc:  'asset/Plateau/Case/1.png',
                    title:     'case + `X`',
                    id: 'plateau',
                    btnText:   'Voir Propriete',
                    btnClass:  'btn-casePlateau'
                },
				{
				    imageSrc:  'asset/Plateau/Case/2.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/2.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/3.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/4.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/5.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/6.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/7.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/8.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/9.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/10.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/11.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/12.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/13.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},

				{
				    imageSrc:  'asset/Plateau/Case/14.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/15.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/16.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				},
				{
				    imageSrc:  'asset/Plateau/Case/17.png',
				    title:     'case + `X`',
				    id: 'plateau',
				    btnText:   'Voir Propriete',
				    btnClass:  'btn-casePlateau'
				}																																																																				
                ];


/**
 * Injection dynamique dans le DOM
 */
function injecterContenuIndex(contenu) {
    const zonePlateau = document.getElementById('plateau');
    if (!zonePlateau) return;

    let html = PLATEAU.map(p => genererCardHTML(p)).join('');

    zonePlateau.innerHTML = html;

}

function genererCardHTML(plateauP) {
    return `
        <div class="case-app">
            <div class="case-image-box">
                <div class="case-image-fallback" style="background: linear-gradient(135deg,#1c2330,#2a3444);">
                    <img src="${plateauP.imageSrc}" 				
						alt="${plateauP.title}" 
				        title="Cliquez pour voir les détails"
				        onclick="afficherDetails('${plateauP.title}')" 
				        style="cursor: pointer;">
                </div>
				<!--<button class="btn-case ${plateauP.btnClass}">${plateauP.btnText} →</button>-->
            </div>
        </div>
    `;
}

document.addEventListener('DOMContentLoaded', () => {
    injecterContenuIndex();
});

// La fonction qui sera appelée au clic
function afficherDetails(nom) {
    alert("Détails de la case : " + nom);
}