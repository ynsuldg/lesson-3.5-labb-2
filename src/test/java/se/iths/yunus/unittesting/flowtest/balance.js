import {expect, test} from '@playwright/test';

test.describe('Testing our page', () => {

    test('The page is accessable', async ({page}) => {
        await page.goto('http://localhost:8080/balance');
        await expect(page).toHaveURL(/balance/);
    });

    test('Page loads correctly', async ({page}) => {
        await page.goto('http://localhost:8080/balance');
        await expect(page).toHaveTitle(/Balance/);  // titeln på sidan
        await expect(page.locator('h1')).toHaveText('The balance'); // rubrik på sidan
    });

    test('The balance shows correctly in the HTML', async ({page}) => {
        await page.goto('http://localhost:8080/balance');
        const saldo = await page.locator('').textContent();
        expect(parseInt(saldo)).toBeGreaterThanOrEqual(0); // saldot ska vara >= 0
    });

});