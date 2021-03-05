package theTimeEater.vfx.cardManip;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.ExhaustBlurEffect;
import com.megacrit.cardcrawl.vfx.ExhaustEmberEffect;

public class UnExhaustCardEffect extends AbstractGameEffect {
    private AbstractCard c;
    private static final float DUR = 1.0F;

    public UnExhaustCardEffect(AbstractCard c) {
        this.duration = 1.0F;// 17
        this.c = c;// 18
    }// 19

    public void update() {
        if (this.duration == 1.0F) {// 22
            CardCrawlGame.sound.play("CARD_EXHAUST", 0.2F);// 23

            int i;
            for(i = 0; i < 90; ++i) {// 24
                AbstractDungeon.effectsQueue.add(new ExhaustBlurEffect(this.c.current_x, this.c.current_y));// 25
            }

            for(i = 0; i < 50; ++i) {// 27
                AbstractDungeon.effectsQueue.add(new ExhaustEmberEffect(this.c.current_x, this.c.current_y));// 28
            }
        }

        this.duration -= Gdx.graphics.getDeltaTime();// 32
        if (!this.c.fadingOut && this.duration < 0.7F && !AbstractDungeon.player.hand.contains(this.c)) {// 33 34
            this.c.fadingOut = true;// 35
        }

        if (this.duration < 0.0F) {// 38
            this.isDone = true;// 39
            this.c.resetAttributes();// 40
        }

    }// 42

    public void render(SpriteBatch sb) {
        this.c.render(sb);// 46
    }// 47

    public void dispose() {
    }// 52
}
